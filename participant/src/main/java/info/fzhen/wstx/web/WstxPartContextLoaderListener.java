package info.fzhen.wstx.web;

import info.fzhen.wstx.coordinator.CoordinatorManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WstxPartContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"wstx-parti.xml"});
		CoordinatorManager coordinator = (CoordinatorManager)ctx.getBean("coordinator");
		CoordinatorManager.setInstance(coordinator);
		
		ctx.close();		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
