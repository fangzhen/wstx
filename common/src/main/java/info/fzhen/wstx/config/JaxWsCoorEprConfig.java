package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsCoorEprConfig extends AbstractJaxWsEprConfig implements CoorEprConfig{
	private EndpointImpl activationEp;
	private EndpointImpl registrationEp;
	
	@Override
	public String getRegistrationService() {
		return getPrefixedServicesUrl() + registrationEp.getAddress();
	}

	@Override
	public String getActivationService() {
		return getPrefixedServicesUrl() + activationEp.getAddress();
	}

	public void setActivationEp(EndpointImpl activationEp) {
		this.activationEp = activationEp;
	}

	public void setRegistrationEp(EndpointImpl registrationEp) {
		this.registrationEp = registrationEp;
	}

}
