package info.fzhen.wstx.test.wscoor;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.test.ClientProxies;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.testng.annotations.Test;

public class ActivationPortIT {
	private ClientProxies proxies = ClientProxies.getInstance();

	@Test
	public void testBasic() {
		ActivationPortType port = (ActivationPortType) proxies.getActivationService();
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ccc.setCoordinationType(CoordinationType.WSAT.getText());
		CreateCoordinationContextResponseType res = port.createCoordinationContextOperation(ccc);
		CoordinationContext coordinationContext = res.getCoordinationContext();
		assert coordinationContext.getCoordinationType().equals(CoordinationType.WSAT.getText());
	}

}
