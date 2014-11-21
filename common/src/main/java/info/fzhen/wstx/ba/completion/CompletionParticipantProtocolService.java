package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.ba.WsBusinessActicity;
import info.fzhen.wstx.ba.port.BaCompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

public class CompletionParticipantProtocolService extends AbstractParticipantProtocolService
		<CompletionInitiator, BaCompletionCoordinatorPortType, CompletionParticipantProtocolService.State>{
	private WsBusinessActicity activity;

	public void setActivity(WsBusinessActicity activity) {
		this.activity = activity;
	}

	public WsBusinessActicity getActivity() {
		return activity;
	}

	public void completed() {
		activity.completed();
	}

	public void complete() {
		BaCompletionCoordinatorPortType coor = getCoordinatorProxy(BaCompletionCoordinatorPortType.class);
		coor.completeOperation(new NotificationType());
	}

	public static enum State implements StateEnum{

	}
}
