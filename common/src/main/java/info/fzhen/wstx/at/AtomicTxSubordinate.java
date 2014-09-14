package info.fzhen.wstx.at;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * subordinate
 */
public class AtomicTxSubordinate extends AbstractActivityCoordinatorContext {
	private CreateCoordinationContextType.CurrentContext superiorCtx;
	private State state;

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		return null;
	}

	public void setSuperiorCtx(CreateCoordinationContextType.CurrentContext superiorCtx) {
		this.superiorCtx = superiorCtx;
	}

	public void setState(State state){
		this.state = state;
	}

	public static enum State implements StateEnum {
		None,
		Active,
		VolatilePraparing,
		VolatileAllPrepared,
		DurablePreparing,
		DurableAllPrepared,
		PreparedSuccess,
		Committing,
		VolatileAllCommitted,
		DurableAllCommitted,
		AllCommitted,
		Aborting
	}
}
