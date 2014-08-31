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

/**
 * Coordiantor side initiator manager.
 */
public class AtInitiatorCoorManager {
    private static final Log __LOG = LogFactory.getLog(AtInitiatorCoorManager.class);

    private static AtInitiatorCoorManager instance;
    private AtCoorEprConfig eprConfig;

    Map<String, AtInitiatorCoor> initiatorCoors = new HashMap<>();

    public static AtInitiatorCoorManager getInstance(){
        if (instance == null){
            if (__LOG.isErrorEnabled()){
                __LOG.error("Initiator Manager(InitiatorCoorManager) " +
                        "hasn't been initialized. It should be initiated " +
                        "by Spring as singleton. Please check your conf file.");
            }
            throw new WstxRtException("Initiator Manager(InitiatorCoorManager) " +
                        "hasn't been initialized. It should be initiated " +
                        "by Spring as singleton. Please check your conf file.");
        }
        return instance;
    }

    public RegisterResponseType registerInitiator(AtomicTxCoordinator activity, RegisterType regPara){
        AtInitiatorCoor initiatorCoor = new AtInitiatorCoor();
        initiatorCoor.setActivity(activity);
        activity.setInitiatorCoor(initiatorCoor);
        initiatorCoor.setParticipantEpr(regPara.getParticipantProtocolService());

        String privateId = CommonUtils.genPrivateId();
        initiatorCoors.put(privateId, initiatorCoor);

        //construct the response
        String addr =  eprConfig.getAtCompletionCoorAddress();
        EndpointReferenceType initiatorCoorEpr = EprUtils.createCxfEprInstance(
                addr, new PrivateIdType(privateId));
        RegisterResponseType response = new RegisterResponseType();
        response.setCoordinatorProtocolService(initiatorCoorEpr);

        return response;
    }

    public AtInitiatorCoor getInitiatorCoor(String id){
        return initiatorCoors.get(id);
    }

    public static void setInstance(AtInitiatorCoorManager instance) {
        AtInitiatorCoorManager.instance = instance;
    }
}
