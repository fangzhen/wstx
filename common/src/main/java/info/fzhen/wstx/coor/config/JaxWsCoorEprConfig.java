package info.fzhen.wstx.coor.config;

import javax.servlet.ServletContext;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.web.context.ServletContextAware;

public class JaxWsCoorEprConfig implements ServletContextAware, CoorEprConfig{
	private EndpointImpl activationEp;
	private EndpointImpl registrationEp;
	private ServletContext servletContext;
	private String servicesUrl = "services";
	private String prefix;
	
	@Override
	public String getRegistrationService() {
		return getPrefixedServicesUrl() + registrationEp.getAddress();
	}

	@Override
	public String getActivationService() {
		return getPrefixedServicesUrl() + activationEp.getAddress();
	}

	private String getPrefixedServicesUrl(){
		return prefix + servletContext.getContextPath() + "/" + servicesUrl;
	}
	public void setActivationEp(EndpointImpl activationEp) {
		this.activationEp = activationEp;
	}

	public void setRegistrationEp(EndpointImpl registrationEp) {
		this.registrationEp = registrationEp;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public void setServicesUrl(String surl){
		this.servicesUrl = surl;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
