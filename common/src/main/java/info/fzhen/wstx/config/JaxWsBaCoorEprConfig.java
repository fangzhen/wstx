package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsBaCoorEprConfig extends AbstractJaxWsEprConfig implements BaCoorEprConfig{
	private EndpointImpl completionCoordinatorEp;
	private EndpointImpl ccCoordinatorEp;
	private EndpointImpl pcCoordinatorEp;

	@Override
	public String getCompletionCoorAddress() {
		return getPrefixedServicesUrl() + completionCoordinatorEp.getAddress();
	}

	@Override
	public String getCCCoorAddress() {
		return getPrefixedServicesUrl() + ccCoordinatorEp.getAddress();
	}

	@Override
	public String getPCCoorAddress() {
		return getPrefixedServicesUrl() + pcCoordinatorEp.getAddress();
	}

	public void setCompletionCoordinatorEp(EndpointImpl completionCoordinatorEp) {
		this.completionCoordinatorEp = completionCoordinatorEp;
	}

	public void setCcCoordinatorEp(EndpointImpl ccCoordinatorEp) {
		this.ccCoordinatorEp = ccCoordinatorEp;
	}

	public void setPcCoordinatorEp(EndpointImpl pcCoordinatorEp) {
		this.pcCoordinatorEp = pcCoordinatorEp;
	}
}
