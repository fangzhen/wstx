package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractCoordinatorProtocolService;
import info.fzhen.wstx.ba.BaCoordinator;
import info.fzhen.wstx.ba.port.BaCompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

public class CompletionCoordinatorProtocolService extends AbstractCoordinatorProtocolService<
		BaCompletionInitiatorPortType, BaCoordinator>{
	public void complete() {
		activity.completeActivity();
		//TODO check state of the activity
		BaCompletionInitiatorPortType initiator = getParticipantProxy(BaCompletionInitiatorPortType.class);
		initiator.completedOperation(new Notification());
	}
}
