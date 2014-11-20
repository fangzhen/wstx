package info.fzhen.wstx.ba.cc;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.StateEnum;
import org.oasis_open.docs.ws_tx.wsba._2006._06.BusinessAgreementWithCoordinatorCompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

public class CcParticipantProtocolService extends AbstractParticipantProtocolService
		<CcParticipant, BusinessAgreementWithCoordinatorCompletionCoordinatorPortType> {
	public void complete() {
		if (state == State.Active){
			state = State.Completing;
			CcParticipant.Vote vote = participant.complete();
			BusinessAgreementWithCoordinatorCompletionCoordinatorPortType coorProxy = getCoordinatorProxy(
					BusinessAgreementWithCoordinatorCompletionCoordinatorPortType.class);
			switch (vote){
				case Completed:
					coorProxy.completedOperation(new NotificationType());
					state = State.Completed;
					break;
				case CannotComplete:
					coorProxy.cannotComplete(new NotificationType());
					state = State.NotCompleting;
					break;
				case Fail:
					//TODO
					throw new RuntimeException("unsupported result of complete operation");

				default:
					break;
			}
		}
	}

	public void close() {
		if (state == State.Completed){
			state = State.Closing;
			participant.close();
			state = State.Closed;
			BusinessAgreementWithCoordinatorCompletionCoordinatorPortType coorProxy = getCoordinatorProxy(
					BusinessAgreementWithCoordinatorCompletionCoordinatorPortType.class);
			coorProxy.closedOperation(new NotificationType());
		}
	}

	public static enum State implements StateEnum{
		None,
		Active,
		Completing,
		NotCompleting,
		Closing, Closed, Completed
	}
}
