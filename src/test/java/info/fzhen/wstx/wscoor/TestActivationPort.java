package info.fzhen.wstx.wscoor;

import static org.junit.Assert.*;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class TestActivationPort {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"info/fzhen/wstx/wscoor/client-beans.xml"});
		ActivationPortType port = (ActivationPortType)context.getBean("Activation");
		CreateCoordinationContextResponseType res = port.createCoordinationContextOperation(new CreateCoordinationContextType());
		assertEquals(res, null);
	}

}
