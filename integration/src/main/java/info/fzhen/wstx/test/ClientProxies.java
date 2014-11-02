package info.fzhen.wstx.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Client proxies of web services that are used by tests
 */
public class ClientProxies {
	private static final Log __LOG = LogFactory.getLog(ClientProxies.class);
	private static ClientProxies instance = new ClientProxies();

	private ClassPathXmlApplicationContext context;
	private ClassPathXmlApplicationContext services;

	private ClientProxies(){
		context = new ClassPathXmlApplicationContext(
				new String[]{"coor-beans.xml"});
		services = new ClassPathXmlApplicationContext(
				new String[]{"service-beans.xml"});
		if (__LOG.isDebugEnabled()) {
			__LOG.debug("Service bean configurations read.");
		}
	}
	public static ClientProxies getInstance(){
		return instance;
	}

	public ActivationPortType getActivationService(){
		return (ActivationPortType) context.getBean("activationService");
	}

	public <T> T getService(String name){
		return (T)services.getBean(name);
	}

	@Override
	public void finalize()throws Throwable{
		super.finalize();
		context.close();
		services.close();
	}
}
