package info.fzhen.wstx.config;

public interface ATPartEprConfig extends EprConfig{
	/**
	 * Return Initiator participant address
	 * @return
	 */
	String getInitiatorAddress();

	/**
	 * Return Volatile 2PC participant address
	 * @return
	 */
	String getVolatile2PcPtcpAddress();

	/**
	 * Return Durable 2PC participant address
	 * @return
	 */
	String getDuarble2PcPtcpAddress();
}
