package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractCoordinatorProtocolService;
import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.ba.BaCoordinator;
import info.fzhen.wstx.ba.port.BaCompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

public class CompletionCoordinatorProtocolService extends AbstractCoordinatorProtocolService<
		BaCompletionInitiatorPortType, BaCoordinator, CompletionCoordinatorProtocolService.State>{
	public void complete() {
		activity.completeActivity();
		//TODO check state of the activity
		BaCompletionInitiatorPortType initiator = getParticipantProxy(BaCompletionInitiatorPortType.class);
		initiator.completedOperation(new NotificationType());
	}

	/**
	 * The activity has closed successfully
	 */
	public void close() {
		BaCompletionInitiatorPortType initiator = getParticipantProxy(BaCompletionInitiatorPortType.class);
		initiator.completedOperation(new NotificationType());
	}

	public static class State implements StateEnum{

	}
}
