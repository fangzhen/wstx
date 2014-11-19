package info.fzhen.wstx.config;


import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsBaPartEprConfig extends AbstractJaxWsEprConfig implements BaPartEprConfig{
	private EndpointImpl initiatorEp;
	private EndpointImpl ccParticipantEp;
	private EndpointImpl pcParticipantEp;

	@Override
	public String getInitiatorAddress() {
		return getPrefixedServicesUrl() + initiatorEp.getAddress();
	}

	@Override
	public String getCCPtcpAddress() {
		return getPrefixedServicesUrl() + ccParticipantEp.getAddress();
	}

	@Override
	public String getPCPtcpAddress() {
		return getPrefixedServicesUrl() + pcParticipantEp.getAddress();
	}

	public void setInitiatorEp(EndpointImpl initiatorEp) {
		this.initiatorEp = initiatorEp;
	}

	public void setCcParticipantEp(EndpointImpl ccParticipantEp) {
		this.ccParticipantEp = ccParticipantEp;
	}

	public void setPcParticipantEp(EndpointImpl pcParticipantEp) {
		this.pcParticipantEp = pcParticipantEp;
	}
}
