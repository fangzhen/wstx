package info.fzhen.wstx.service;

import info.fzhen.wstx.context.ActivityCoordinatorContext;
import info.fzhen.wstx.coordinator.Coordinator;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * Activation service. 
 * @author fangzhen
 *
 */
public class ActivationService {
	private static Coordinator coordinator = Coordinator.getInstance();
	public CreateCoordinationContextResponseType createCoordinationContextOperation(
			CreateCoordinationContextType ccc) {
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
