package info.fzhen.wstx.at;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.twopc.At2pcSubordinateManager;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * subordinate
 */
public class AtomicTxSubordinate extends AbstractActivityCoordinatorContext {
	private static final Log __LOG = LogFactory.getLog(AtomicTxSubordinate.class);

	private CoordinationContext superiorCtx;
	private State state;

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		AtProtocol protocol = AtProtocol.fromString(protocolId);
		if (__LOG.isInfoEnabled()){
			__LOG.info("register protocol " + protocolId + " on subordinate");
		}
		RegisterResponseType response = null;
		switch (protocol){
			case DURABLE2PC:
				response = At2pcSubordinateManager.getInstance().registerDurable2Pc(this, registerPara);
				break;
			case VOLATILE2PC:
				response = At2pcSubordinateManager.getInstance().registerVolatile2Pc(this, registerPara);
				break;
			case COMPLETION:
				throw new WstxRtException("Cannot register completion protocol on subordinate");
		}
		return response;
	}

	public void setSuperiorCtx(CoordinationContext superiorCtx) {
		this.superiorCtx = superiorCtx;
	}

	public CoordinationContext getSuperiorCtx() {
		return superiorCtx;
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
