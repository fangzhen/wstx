package info.fzhen.wstx.config;

import org.apache.cxf.jaxws.EndpointImpl;

public class JaxWsAtCoorEprConfig extends AbstractJaxWsEprConfig implements AtCoorEprConfig{
	private EndpointImpl atCompletionCoorEp;
	private EndpointImpl at2PcCoorEp;
	
	@Override
	public String getAtCompletionCoorAddress() {
		return getPrefixedServicesUrl() + atCompletionCoorEp.getAddress();
	}

	@Override
	public String getAt2PcCoorAddress() {
		return getPrefixedServicesUrl() + at2PcCoorEp.getAddress();
	}
	
	public void setAtCompletionCoorEp(EndpointImpl atCompletionCoorEp) {
		this.atCompletionCoorEp = atCompletionCoorEp;
	}

	public void setAt2PcCoorEp(EndpointImpl at2PcCoorEp) {
		this.at2PcCoorEp = at2PcCoorEp;
	}


}
