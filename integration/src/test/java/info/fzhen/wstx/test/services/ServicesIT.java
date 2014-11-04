package info.fzhen.wstx.test.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Test services used.
 *
 * @author fangzhen
 */
public class ServicesIT {

	@Test
	public void testHelloService() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"service-beans.xml"});
		HelloService port = (HelloService) context.getBean("helloService");
		assert "Hello".equals(port.sayHello());
		context.close();
	}
}
