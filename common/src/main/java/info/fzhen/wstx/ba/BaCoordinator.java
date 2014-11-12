package info.fzhen.wstx.ba;

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

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		return null;
	}

	public void setState(State state) {
		this.state = state;
	}

	public enum State {
		Active,
	}
}
