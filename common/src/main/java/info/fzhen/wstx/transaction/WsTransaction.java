package info.fzhen.wstx.transaction;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

public abstract class WsTransaction {
	private ActivationPortType activationSer;
	private CoordinationContext coordinationContext;

	public CoordinationContext getCoordinationContext() {
		return coordinationContext;
	}

	public void setCoordinationContext(CoordinationContext coordinationContext) {
		this.coordinationContext = coordinationContext;
	}

	public ActivationPortType getActivationSer() {
		return activationSer;
	}

	public void setActivationSer(ActivationPortType activationSer) {
		this.activationSer = activationSer;
	}

	public abstract void begin();
}
