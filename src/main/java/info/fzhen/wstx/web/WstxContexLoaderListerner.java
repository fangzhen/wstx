package info.fzhen.wstx.web;

import info.fzhen.wstx.coordinator.Coordinator;
import info.fzhen.wstx.coordinator.PrivateInstanceType;
import info.fzhen.wstx.util.EPRConfiguration;
import info.fzhen.wstx.wscoor.ActivationPort;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.apache.cxf.common.jaxb.JAXBContextCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WstxContexLoaderListerner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"wstx.xml"});
		Coordinator coordinator = (Coordinator)ctx.getBean("coordinator");
		Coordinator.setInstance(coordinator);
		
		ctx.close();
	}

}
