package info.fzhen.wstx.coordinator;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.at.ATCoordinatorContext;
import info.fzhen.wstx.context.CoordinatorContext;

public class Coordinator {
	private CoordinatorContext coordinatorContext;
	public CoordinatorContext createCoordinatorContext(CreateCoordinationContextType ccc){
		switch (ccc.getCoordinationType()) {
		case CoordinationType.WSAT:
			coordinatorContext = new ATCoordinatorContext();
			//TODO build the context
			break;
		case CoordinationType.WSBA:
			break;
		default:
			break;
		}
		return coordinatorContext;
	}

}
