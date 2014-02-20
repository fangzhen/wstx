package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.context.ActivityCoordinatorContext;
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
		Coordinator coordinator;
		coordinator = Coordinator.getInstance();
		ActivityCoordinatorContext coordinatorContext = coordinator.createActivityCoordinatorContext(ccc);
		//TODO null handle
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
