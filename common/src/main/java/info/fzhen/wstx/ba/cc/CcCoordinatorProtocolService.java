package info.fzhen.wstx.ba.cc;

import info.fzhen.wstx.AbstractCoordinatorProtocolService;
import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.ba.BaCoordinator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsba._2006._06.BusinessAgreementWithCoordinatorCompletionParticipantPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

public class CcCoordinatorProtocolService extends AbstractCoordinatorProtocolService
		<BusinessAgreementWithCoordinatorCompletionParticipantPortType,BaCoordinator, CcCoordinatorProtocolService.State>{
	private static final Log __LOG = LogFactory.getLog(CcCoordinatorProtocolService.class);
	public void complete() {
		if (state == State.Active){
			if (__LOG.isInfoEnabled()){
				__LOG.info("Send complete message to " +
						participantEpr.getAddress().getValue());
			}
			BusinessAgreementWithCoordinatorCompletionParticipantPortType partProxy =
					getParticipantProxy(BusinessAgreementWithCoordinatorCompletionParticipantPortType.class);
			partProxy.completeOperation(new NotificationType());
			state = State.Completing;
		}
	}

	public void completed() {
		if (state == State.Completing){
			getActivity().completedCC(this);
			state = State.Completed;
		}
	}

	public void close() {
		if (state == State.Completed){
			BusinessAgreementWithCoordinatorCompletionParticipantPortType partProxy =
					getParticipantProxy(BusinessAgreementWithCoordinatorCompletionParticipantPortType.class);
			partProxy.closeOperation(new NotificationType());
			state = State.Closing;

		}
	}

	public void closed() {
		if (state == State.Closing){
			getActivity().closedCC(this);
			state = State.closed;
		}
	}

	public static enum State implements StateEnum{
		None,
		Active,
		Completing,
		Completed,
		Closing,
		closed, Compensating
	}
}
