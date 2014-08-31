package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.util.CommonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

import java.util.HashMap;
import java.util.Map;

public class At2pcCoorManager extends AtAbstractCoorManager{
    private static final Log __LOG = LogFactory.getLog(At2pcCoorManager.class);

    private static At2pcCoorManager instance;

    private Map<String, At2pcCoor> at2pcCoors = new HashMap<>();

    public static At2pcCoorManager getInstance(){
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

    /**
     * Register 2PC participant
     * @param activity the activity that involved
     * @param regPara register parameter
     * @return
     */
    public RegisterResponseType register2pcParticipant(AtomicTxCoordinator activity, RegisterType regPara){
        At2pcCoor at2pcCoor = new At2pcCoor();
        at2pcCoor.setActivity(activity);
        activity.addD2pdCoor(at2pcCoor);
        at2pcCoor.setParticipantEpr(regPara.getParticipantProtocolService());

        String privateId = CommonUtils.genPrivateId();
        at2pcCoors.put(privateId, at2pcCoor);

        RegisterResponseType response = constructRegisterResponse(privateId);
        return  response;
    }

    public static void setInstance(At2pcCoorManager instance) {
        At2pcCoorManager.instance = instance;
    }
}
