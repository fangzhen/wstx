package info.fzhen.wstx.at;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * subordinate
 */
public class AtomicTxSubordinate extends AtomicTxCoordinator {
	private CreateCoordinationContextType.CurrentContext superiorCtx;

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		return null;
	}

	public void setSuperiorCtx(CreateCoordinationContextType.CurrentContext superiorCtx) {
		this.superiorCtx = superiorCtx;
	}
}
