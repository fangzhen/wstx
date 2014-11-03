package info.fzhen.wstx.at.completion;


import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.at.AtProtocolServiceCoor;
import info.fzhen.wstx.at.AtomicTxCoordinator;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

/**
 * Protocol service of AT Initiator
 */
public class AtInitiatorCoor extends AtProtocolServiceCoor<CompletionInitiatorPortType, AtomicTxCoordinator> {

	public void commit() {
		activity.commitActivity();
		CompletionInitiatorPortType initiator = getParticipantProxy(CompletionInitiatorPortType.class);
		initiator.committedOperation(new Notification());
	}

	public static enum State implements StateEnum {
		None,
		Active,
		Completing
	}
}
