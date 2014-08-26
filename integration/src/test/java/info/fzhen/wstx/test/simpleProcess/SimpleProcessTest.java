package info.fzhen.wstx.test.simpleProcess;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class SimpleProcessTest {

	@Test (groups = {"integration"})
	public void executeProcess() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "service-beans.xml" });
		Process port = (Process) context.getBean("helloProcess");
		port.execute();
		
		context.close();
	}
}
