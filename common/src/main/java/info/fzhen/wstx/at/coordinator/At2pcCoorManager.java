package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.util.CommonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

public class At2pcCoorManager extends AtAbstractCoorManager{
    private static final Log __LOG = LogFactory.getLog(At2pcCoorManager.class);

    private static At2pcCoorManager instance;

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

    /**register durable 2PC protocol */
    public RegisterResponseType registerD2pcParticipant(AtomicTxCoordinator activity, RegisterType regPara) {
        return register2pcParticipant(activity, regPara, false);
    }

    /**register durable 2PC protocol */
    public RegisterResponseType registerV2pcParticipant(AtomicTxCoordinator activity, RegisterType regPara) {
        return register2pcParticipant(activity, regPara, true);
    }
    /**
     * Register 2PC participant
     * @param activity the activity that involved
     * @param regPara register parameter
     * @param v true if is volatile 2PC
     * @return
     */
    public RegisterResponseType register2pcParticipant(AtomicTxCoordinator activity, RegisterType regPara, boolean v){
        At2pcCoor at2pcCoor = new At2pcCoor();
        at2pcCoor.setActivity(activity);
        if (v){
            activity.addV2pcCoor(at2pcCoor);
        }else {
            activity.addD2pcCoor(at2pcCoor);
        }
        at2pcCoor.setParticipantEpr(regPara.getParticipantProtocolService());

        String privateId = CommonUtils.genPrivateId();
        managedCoordinators.put(privateId, at2pcCoor);

        RegisterResponseType response = constructRegisterResponse(privateId);
        return  response;
    }

    public static void setInstance(At2pcCoorManager instance) {
        At2pcCoorManager.instance = instance;
    }
}
