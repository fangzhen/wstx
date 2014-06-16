package info.fzhen.wstx.wscoor;

import static org.junit.Assert.*;

import info.fzhen.wstx.Constants;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class TestActivationPort {

//	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"client-beans.xml"});
		ActivationPortType port = (ActivationPortType)context.getBean("activation");
		System.out.println("TestActivationPort*** " + port.getClass());
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ccc.setCoordinationType(Constants.WSAT);
		CreateCoordinationContextResponseType res = port.createCoordinationContextOperation(ccc);
		CoordinationContext coordinationContext = res.getCoordinationContext();
		assertEquals(coordinationContext.getCoordinationType(),Constants.WSAT);
		System.out.println("TestActivationPort*** " + coordinationContext.getRegistrationService());
		
		context.close();
	}

}
