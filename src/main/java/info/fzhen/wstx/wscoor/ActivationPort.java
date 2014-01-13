package info.fzhen.wstx.wscoor;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

@WebService(endpointInterface="org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType")
public class ActivationPort implements ActivationPortType{

	@Override
	public CreateCoordinationContextResponseType createCoordinationContextOperation(
			CreateCoordinationContextType parameters) {
		return null;
	}

}
