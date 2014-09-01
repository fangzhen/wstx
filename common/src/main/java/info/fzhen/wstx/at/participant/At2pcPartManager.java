package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.at.coordinator.AtProtocol;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

/**
 * Participant side durable2Pc participant manager. This manager is
 * singleton to the site which manages all the participants.
 */
public class At2pcPartManager extends AtAbstractPartManager{
    /** singleton */
    private static At2pcPartManager instance;

    public static At2pcPartManager getInstance(){
        synchronized (At2pcPartManager.class){
            if (instance == null){
                instance = new At2pcPartManager();
            }
        }
        return instance;
    }

    /**
     * register durable 2PC protocol
     * @param coorContext transaction coordination context
     * @param participant the participant to be registered.
     */
    public void registerDurable2Pc(CoordinationContext coorContext, At2pcPart participant){
    }

    public void registerVolatile2Pc(CoordinationContext coorContext, At2pcPart participant){
        register2Pc(coorContext, participant, true);
    }

    /**
     *
     * @param coorContext
     * @param participant
     * @param isVolatile is volatile 2PC or not (durable 2PC)
     */
    private void register2Pc(CoordinationContext coorContext, At2pcPart participant, boolean isVolatile) {
        String protocolId;
        if (isVolatile){
            protocolId = AtProtocol.VOLATILE2PC.getText();
        }else{
            protocolId = AtProtocol.DURABLE2PC.getText();
        }
        doRegister(coorContext, participant, protocolId);
    }
}
