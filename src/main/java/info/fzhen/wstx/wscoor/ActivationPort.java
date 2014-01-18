package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.CoordinationType;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

@WebService(endpointInterface="org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType")
public class ActivationPort implements ActivationPortType{

	@Override
	public CreateCoordinationContextResponseType createCoordinationContextOperation(
			CreateCoordinationContextType ccc) {
		switch (ccc.getCoordinationType()) {
		case CoordinationType.WSAT:
			
			break;
		case CoordinationType.WSBA:
			break;
		default:
			//TODO: not supported. log it.
			break;
		}
		return null;
	}

}
