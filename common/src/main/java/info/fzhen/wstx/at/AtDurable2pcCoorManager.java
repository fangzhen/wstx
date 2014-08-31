package info.fzhen.wstx.at;


import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.config.AtCoorEprConfig;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.CommonUtils;
import info.fzhen.wstx.util.EprUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

import java.util.HashMap;
import java.util.Map;

public class AtDurable2pcCoorManager {
    private static final Log __LOG = LogFactory.getLog(AtDurable2pcCoorManager.class);

    private static AtDurable2pcCoorManager instance;
    private AtCoorEprConfig eprConfig;

    Map<String, AtDurable2pcCoor> durable2pcCoors = new HashMap<>();

    public static AtDurable2pcCoorManager getInstance(){
        if (instance == null){
            if (__LOG.isErrorEnabled()){
                __LOG.error("Durable 2PC Manager(AtDurable2pcCoorManager) " +
                        "hasn't been initialized. It should be initiated " +
                        "by Spring as singleton. Please check your conf file.");
            }
            throw new WstxRtException("Durable 2PC Manager(AtDurable2pcCoorManager) " +
                        "hasn't been initialized. It should be initiated " +
                        "by Spring as singleton. Please check your conf file.");
        }
        return instance;
    }

    public RegisterResponseType registerD2pcParticipant(AtomicTxCoordinator activity, RegisterType regPara){
        AtDurable2pcCoor d2pcCoor = new AtDurable2pcCoor();
        d2pcCoor.setActivity(activity);
        activity.addD2pdCoor(d2pcCoor);
        d2pcCoor.setParticipantEpr(regPara.getParticipantProtocolService());

        String privateId = CommonUtils.genPrivateId();
        durable2pcCoors.put(privateId, d2pcCoor);

        //construct the response
        String addr = eprConfig.getAt2PcCoorAddress();
        EndpointReferenceType d2pcCoorEpr = EprUtils.createCxfEprInstance(
                addr, new PrivateIdType(privateId));
        RegisterResponseType response = new RegisterResponseType();
        response.setCoordinatorProtocolService(d2pcCoorEpr);
        return  response;
    }
}
