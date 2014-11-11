package info.fzhen.wstx;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

public abstract class WsTransaction {
	private CoordinationContext coordinationContext;
	/**
	 * client proxy of coordinator activation service
	 */
	private ActivationPortType activationSer;


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
