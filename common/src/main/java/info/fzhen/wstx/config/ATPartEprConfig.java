package info.fzhen.wstx.config;

public interface ATPartEprConfig extends EprConfig {
	/**
	 * Return Initiator participant address
	 *
	 * @return
	 */
	String getInitiatorAddress();

	/**
	 * Return 2PC participant address
	 *
	 * @return
	 */
	String getTwoPcPtcpAddress();
}
