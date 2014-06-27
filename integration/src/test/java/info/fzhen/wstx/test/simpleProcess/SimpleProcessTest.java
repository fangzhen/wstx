package info.fzhen.wstx.test.simpleProcess;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleProcessTest {

	@Test
	public void executeProcess() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "client-beans.xml" });
		Process port = (Process) context
				.getBean("helloProcess");
		System.out.println("Test simple Process*** " + port.getClass());
		port.execute();
		
		context.close();
	}
}
