package info.fzhen.wstx.config;

public interface BaPartEprConfig extends EprConfig{
	String getInitiatorAddress();

	String getCCPtcpAddress();

	String getPCPtcpAddress();
}
