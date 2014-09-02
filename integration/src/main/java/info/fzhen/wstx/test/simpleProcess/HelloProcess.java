package info.fzhen.wstx.test.simpleProcess;

import info.fzhen.wstx.test.services.HelloService;
import info.fzhen.wstx.at.participant.TransactionFactory;
import info.fzhen.wstx.at.participant.AtInitiatorPartManager;
import info.fzhen.wstx.at.participant.WsatTransaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.WebService;
import javax.xml.ws.BindingProvider;

@WebService
public class HelloProcess extends TransactionalProcess{
	private static final Log __log = LogFactory.getLog(TransactionalProcess.class);
	
	private HelloService helloSer;
	private ClassPathXmlApplicationContext context;
	private ClassPathXmlApplicationContext services;
	
	public HelloProcess() {
	}

	@Override
	public void execute(){
		context = new ClassPathXmlApplicationContext(
				new String[]{"coor-beans.xml"});
		services = new ClassPathXmlApplicationContext(
				new String[]{"service-beans.xml"});
		if (__log.isDebugEnabled()){
			__log.debug("Service bean configurations read.");
		}
		ActivationPortType activationSer = (ActivationPortType)context.getBean("activationService");
		WsatTransaction transaction = TransactionFactory.getInstance().createWsatTransaction(activationSer);
		transaction.begin();
		//register WSAT completion protocol
		
		AtInitiatorPartManager manager = AtInitiatorPartManager.getInstance();
		manager.registerInitiator(transaction);

		//then send application messages with CC
		helloSer = (HelloService)services.getBean("helloService");
		addTransactionInfo2Client((BindingProvider) helloSer, transaction);
		helloSer.sayHello();

		transaction.commit();

        context.close();
		services.close();
	}

}
