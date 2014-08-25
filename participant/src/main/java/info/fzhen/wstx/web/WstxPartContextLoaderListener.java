package info.fzhen.wstx.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WstxPartContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"wstx-parti.xml"});
//		ctx.close();		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
