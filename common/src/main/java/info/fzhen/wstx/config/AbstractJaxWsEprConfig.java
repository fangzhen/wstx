package info.fzhen.wstx.config;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public abstract class AbstractJaxWsEprConfig implements ServletContextAware {
	private ServletContext servletContext;
	private String servicesUrl = "services";
	private String prefix;

	protected String getPrefixedServicesUrl() {
		return prefix + servletContext.getContextPath() + "/" + servicesUrl;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setServicesUrl(String surl) {
		this.servicesUrl = surl;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
