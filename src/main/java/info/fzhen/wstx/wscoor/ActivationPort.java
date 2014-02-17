package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.context.CoordinatorContext;
import info.fzhen.wstx.coordinator.Coordinator;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

@WebService(endpointInterface="org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType")
public class ActivationPort implements ActivationPortType{

	@Override
	public CreateCoordinationContextResponseType createCoordinationContextOperation(
			CreateCoordinationContextType ccc) {
		Coordinator coordinator = new Coordinator();
		CoordinatorContext coordinatorContext = coordinator.createCoordinatorContext(ccc);
		CoordinationContext coordinationContext = coordinatorContext.buildCoordinationContext();
		CreateCoordinationContextResponseType response = buildCreateCoordinationContextResponse(coordinationContext);
		return response;
	}

	/**
	 * Activation services specific response info.--nothing
	 * @param coordinationContext
	 * @return
	 */
	private CreateCoordinationContextResponseType buildCreateCoordinationContextResponse(
			CoordinationContext coordinationContext) {
		CreateCoordinationContextResponseType response = new CreateCoordinationContextResponseType();
		response.setCoordinationContext(coordinationContext);
		return response;
	}

}
