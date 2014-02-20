package info.fzhen.wstx.web;

import info.fzhen.wstx.coordinator.Coordinator;
import info.fzhen.wstx.util.EPRConfiguration;
import info.fzhen.wstx.wscoor.ActivationPort;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WstxContexLoaderListerner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"wstx.xml"});
		Coordinator coordinator = (Coordinator)ctx.getBean("coordinator");
		Coordinator.setInstance(coordinator);
	}

}
