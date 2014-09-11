package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.at.AtomicTxCoordinator;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

public class ActivityCoordinatorFactory {
	/**
	 * Factory method that create new instance of Atomic Tx.
	 *
	 * @param ccc
	 * @param pirvateId
	 * @return
	 */
	public AtomicTxCoordinator createAtActivity(CreateCoordinationContextType ccc, String pirvateId) {
		AtomicTxCoordinator activityCoordinatorContext = new AtomicTxCoordinator();
		activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
		if (ccc.getExpires() != null) {
			activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
		}
		activityCoordinatorContext.setPrivateId(pirvateId);
		activityCoordinatorContext.setState(AtomicTxCoordinator.State.Active);
		return activityCoordinatorContext;
	}

}
