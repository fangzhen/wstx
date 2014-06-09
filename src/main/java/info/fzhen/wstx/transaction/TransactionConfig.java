package info.fzhen.wstx.transaction;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

public class TransactionConfig {
	private ActivationPortType activationSer;
	private String coordinationType;
	public ActivationPortType getActivationSer() {
		return activationSer;
	}

	public void setActivationSer(ActivationPortType activationSer) {
		this.activationSer = activationSer;
	}

	public String getCoordinationType() {
		return coordinationType;
	}

	public void setCoordinationType(String coordinationType) {
		this.coordinationType = coordinationType;
	}
}
