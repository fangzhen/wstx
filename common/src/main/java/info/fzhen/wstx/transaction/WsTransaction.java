package info.fzhen.wstx.transaction;

public abstract class WsTransaction {
	protected TransactionConfig config;
	public TransactionConfig getConfig() {
		return config;
	}
	public void setConfig(TransactionConfig config) {
		this.config = config;
	}
	
	public abstract void begin();
}
