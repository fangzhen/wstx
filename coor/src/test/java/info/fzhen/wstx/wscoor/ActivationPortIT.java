package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.CoordinationType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class ActivationPortIT {

	@Test(groups = {"integration"})
	public void testBasic() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"client-beans.xml"});
		ActivationPortType port = (ActivationPortType) context.getBean("activation");
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ccc.setCoordinationType(CoordinationType.WSAT.getText());
		CreateCoordinationContextResponseType res = port.createCoordinationContextOperation(ccc);
		CoordinationContext coordinationContext = res.getCoordinationContext();
		assert coordinationContext.getCoordinationType().equals(CoordinationType.WSAT.getText());

		context.close();
	}

}
