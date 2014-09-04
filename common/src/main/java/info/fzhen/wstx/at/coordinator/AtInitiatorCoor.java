package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.StateEnum;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

public class AtInitiatorCoor extends AtProtocolServiceCoor<CompletionInitiatorPortType>{

    public void commit() {
        activity.commitActivity();
        CompletionInitiatorPortType initiator = getParticipantProxy(CompletionInitiatorPortType.class);
		initiator.committedOperation(new Notification());
    }

    public static enum  State implements StateEnum {
        None,
        Active,
        Completing
    }
}
