package info.fzhen.wstx.test.simpleProcess;

import info.fzhen.wstx.test.services.HelloService;
import info.fzhen.wstx.transaction.TransactionFactory;
import info.fzhen.wstx.transaction.WsatTransaction;
import info.fzhen.wstx.transaction.WsatTxManager;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebService
public class HelloProcess implements Process{
	private HelloService helloSer;
	private ClassPathXmlApplicationContext context;
	private ClassPathXmlApplicationContext services;
	
	public HelloProcess() {
	}

	@Override
	public void execute() {
		context = new ClassPathXmlApplicationContext(
				new String[]{"coor-beans.xml"});
		services = new ClassPathXmlApplicationContext(
				new String[]{"service-beans.xml"});
		
		ActivationPortType activationSer = (ActivationPortType)context.getBean("activationService");
		WsatTransaction transaction = TransactionFactory.getInstance().createWsatTransaction(activationSer);
		transaction.begin();
		//register WSAT completion protocol
		
		WsatTxManager manager = WsatTxManager.getInstance();
		manager.registerInitiator(transaction);

		//then send application messages with CC
		helloSer = (HelloService)services.getBean("helloService");
		helloSer.sayHello();
		
		transaction.commit();
		context.close();
		services.close();
	}
	
}
