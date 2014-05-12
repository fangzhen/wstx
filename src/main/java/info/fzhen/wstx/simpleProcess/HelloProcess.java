package info.fzhen.wstx.simpleProcess;

import info.fzhen.wstx.HelloService;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebService
public class HelloProcess implements Process{
	private HelloService helloSer;
	ClassPathXmlApplicationContext context;
	public HelloProcess() {
		context = new ClassPathXmlApplicationContext(
				new String[]{"client-beans.xml"});
		helloSer = (HelloService)context.getBean("hello");
	}

	@Override
	public void execute() {
		System.out.println(helloSer.sayHello());
		context.close();
	}
	
}
