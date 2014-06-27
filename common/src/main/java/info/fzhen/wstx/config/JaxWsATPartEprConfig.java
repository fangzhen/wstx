package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsATPartEprConfig extends AbstractJaxWsEprConfig implements ATPartEprConfig{
	private EndpointImpl initiatorEp;

	@Override
	public String getInitiatorAddress() {
		return getPrefixedServicesUrl() + initiatorEp.getAddress();
	}
	
	public void setInitiatorEp(EndpointImpl initiator){
		this.initiatorEp = initiator;
	}
}
