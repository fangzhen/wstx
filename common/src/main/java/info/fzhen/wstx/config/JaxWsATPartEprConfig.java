package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsATPartEprConfig extends AbstractJaxWsEprConfig implements ATPartEprConfig {
	private EndpointImpl initiatorEp;
	private EndpointImpl twoPcParticipantEp;

	@Override
	public String getInitiatorAddress() {
		return getPrefixedServicesUrl() + initiatorEp.getAddress();
	}

	@Override
	public String getTwoPcPtcpAddress() {
		return getPrefixedServicesUrl() + twoPcParticipantEp.getAddress();
	}

	public void setInitiatorEp(EndpointImpl initiator) {
		this.initiatorEp = initiator;
	}

	public void setTwoPcParticipantEp(EndpointImpl twoPcParticipantEp) {
		this.twoPcParticipantEp = twoPcParticipantEp;
	}
}
