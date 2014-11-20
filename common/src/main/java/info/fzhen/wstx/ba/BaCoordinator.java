package info.fzhen.wstx.ba;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.ba.cc.CcCoordinatorProtocolService;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolService;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

import java.util.ArrayList;
import java.util.List;

/**
 * coordinator side business activity instance
 */
public class BaCoordinator extends AbstractActivityCoordinatorContext {
	private static final Log __LOG = LogFactory.getLog(BaCoordinator.class);

	/** state of this activity*/
	private State state;
	private final Object stateLock = new Object();

	/* Protocol services */
	private CompletionCoordinatorProtocolService completionCoor;
	private final List<CcCoordinatorProtocolService> CcCoors = new ArrayList<>();

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		BaProtocol protocol = BaProtocol.fromString(protocolId);
		RegisterResponseType response = null;
		switch (protocol){
			case COMPLETION:
				response = CompletionCoordinatorProtocolMgr.getInstance()
						.registerInitiator(this, registerPara);
				break;
			case COORDINATOR_COMPLETION:
				break;
			case PARTICIPANT_COMPLETION:
				break;
			default:
				throw new WstxRtException("Unsupported protocol" +
				protocolId);
		}
		return response;
	}

	public void completeActivity() {
		synchronized (stateLock){
			this.state = State.Completing;
			completeCC();
		}
	}

	/**
	 * send complete message to all participants registered
	 * as coordinator completion protocol
	 */
	private void completeCC() {
		for (CcCoordinatorProtocolService service : CcCoors){
			service.complete();
		}
	}

	public void completedCC(CcCoordinatorProtocolService ccCoordinatorProtocolService) {
		updateActivityState();
		if (state == State.Completed){
			closeCC();
		}
	}

	private void closeCC() {
		for (CcCoordinatorProtocolService service : CcCoors){
			service.close();
		}
	}

	private void updateActivityState() {
		State s = State.Completed;
		for (CcCoordinatorProtocolService service : CcCoors){
			switch (service.getState()){
				case Completing:
					s = State.Completing;
					break;
				default:
					//TODO
					break;
			}
		}
		state = s;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setCompletionCoor(CompletionCoordinatorProtocolService completionCoor) {
		this.completionCoor = completionCoor;
	}

	public CompletionCoordinatorProtocolService getCompletionCoor() {
		return completionCoor;
	}

	public void closedCC(CcCoordinatorProtocolService ccCoordinatorProtocolService) {
		updateActivityEndState();
		if (state == State.Closed){
			completionCoor.close();
		}
	}

	private void updateActivityEndState() {
		State s = State.Closed;
		for (CcCoordinatorProtocolService service : CcCoors){
			switch (service.getState()){
				case Closing:
					s = State.Closing;
					break;
				default:
					//TODO
					break;
			}
		}
		state = s;
	}

	public enum State {
		Active,
		Completing,
		Closed, Closing, Completed
	}
}
