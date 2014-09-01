package info.fzhen.wstx.at.participant;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

public abstract class WsTransaction {
	private CoordinationContext coordinationContext;

	public CoordinationContext getCoordinationContext() {
		return coordinationContext;
	}

	public void setCoordinationContext(CoordinationContext coordinationContext) {
		this.coordinationContext = coordinationContext;
	}
	public abstract void begin();
}
