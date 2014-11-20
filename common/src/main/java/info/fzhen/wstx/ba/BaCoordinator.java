package info.fzhen.wstx.ba;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolService;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * coordinator side business activity instance
 */
public class BaCoordinator extends AbstractActivityCoordinatorContext {
	private static final Log __LOG = LogFactory.getLog(BaCoordinator.class);
	private State state;
	private CompletionCoordinatorProtocolService completionCoor;

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

	public enum State {
		Active,
	}
}
