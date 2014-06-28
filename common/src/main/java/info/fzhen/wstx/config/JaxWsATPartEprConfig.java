package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsATPartEprConfig extends AbstractJaxWsEprConfig implements ATPartEprConfig{
	private EndpointImpl initiatorEp;
	private EndpointImpl durable2PcParticipantEp;
	private EndpointImpl volatile2PcParticipantEp;

	@Override
	public String getInitiatorAddress() {
		return getPrefixedServicesUrl() + initiatorEp.getAddress();
	}
	
	@Override
	public String getDuarble2PcPtcpAddress(){
		return getPrefixedServicesUrl() + durable2PcParticipantEp.getAddress();
	}
	
	@Override
	public String getVolatile2PcPtcpAddress(){
		return getPrefixedServicesUrl() + volatile2PcParticipantEp.getAddress();
	}
	
	public void setInitiatorEp(EndpointImpl initiator){
		this.initiatorEp = initiator;
	}
	
	public void setDurable2PcParticipantEp(EndpointImpl durable2PcParticipantEp) {
		this.durable2PcParticipantEp = durable2PcParticipantEp;
	}

	public void setVolatile2PcParticipantEp(EndpointImpl volatile2PcParticipantEp) {
		this.volatile2PcParticipantEp = volatile2PcParticipantEp;
	}
}
