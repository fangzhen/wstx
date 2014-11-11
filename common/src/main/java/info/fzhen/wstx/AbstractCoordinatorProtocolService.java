package info.fzhen.wstx;

import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

/**
 * Abstract class of protocol coordinator service
 *
 * @param <T> type of corresponding participant service port type
 * @param <E> activity type it belongs to
 */
public abstract class AbstractCoordinatorProtocolService<T, E extends AbstractActivityCoordinatorContext> {
	/**
	 * The activity it belongs to
	 */
	protected E activity;

	/**
	 * participant protocol service EPR
	 */
	protected EndpointReferenceType participantEpr;
	/**
	 * state of the coordiantor
	 */
	protected StateEnum state;
	private T PartProxy;

	protected T getParticipantProxy(Class<T> clazz) {
		PartProxy = EprUtils.createWsaddrClientProxy(clazz, participantEpr);
		return PartProxy;
	}

	public E getActivity() {
		return activity;
	}

	public void setActivity(E activity) {
		this.activity = activity;
	}

	public EndpointReferenceType getParticipantEpr() {
		return participantEpr;
	}

	public void setParticipantEpr(EndpointReferenceType participantEpr) {
		this.participantEpr = participantEpr;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
}
