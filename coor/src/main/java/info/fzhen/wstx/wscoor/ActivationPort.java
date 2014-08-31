package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.coordinator.ActivationService;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

import javax.jws.WebService;
/**
 * jax-ws web service wrapper to ActivationService
 * @author fangzhen
 *
 */

@WebService(endpointInterface = "org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType")
public class ActivationPort implements ActivationPortType {
	@Override
	public CreateCoordinationContextResponseType createCoordinationContextOperation(
			CreateCoordinationContextType ccc) {
		CreateCoordinationContextResponseType response = new ActivationService()
				.createCoordinationContextOperation(ccc);
		return response;
	}
}
