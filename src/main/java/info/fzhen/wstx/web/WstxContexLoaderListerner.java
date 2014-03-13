package info.fzhen.wstx.web;

import info.fzhen.wstx.coordinator.Coordinator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
