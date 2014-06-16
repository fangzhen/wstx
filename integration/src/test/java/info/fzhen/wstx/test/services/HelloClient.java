package info.fzhen.wstx.test.services;

import info.fzhen.wstx.test.services.HelloService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloClient {
//	@Test
	public void testSayHello(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"client-beans.xml"});
		HelloService port = (HelloService)context.getBean("hello");
		System.out.println(port.sayHello());
		context.close();
	}
}
