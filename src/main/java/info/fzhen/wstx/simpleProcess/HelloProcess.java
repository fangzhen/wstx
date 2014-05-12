package info.fzhen.wstx.simpleProcess;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.HelloService;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebService
public class HelloProcess implements Process{
	private HelloService helloSer;
	ClassPathXmlApplicationContext context;
	public HelloProcess() {
	}

	@Override
	public void execute() {
		context = new ClassPathXmlApplicationContext(
				new String[]{"client-beans.xml"});
		helloSer = (HelloService)context.getBean("hello");
		ActivationPortType activationSer = (ActivationPortType)context.getBean("activationService");
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ccc.setCoordinationType(Constants.WSAT);
		CreateCoordinationContextResponseType res = activationSer.createCoordinationContextOperation(ccc);
		CoordinationContext coordinationContext = res.getCoordinationContext();				
		//then send application messages with CC
		
		context.close();
	}
	
}
