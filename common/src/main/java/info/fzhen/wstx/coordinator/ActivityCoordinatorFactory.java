package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.at.AtomicTxCoordinator;
import info.fzhen.wstx.at.AtomicTxSubordinate;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

public class ActivityCoordinatorFactory {
	/**
	 * Factory method that create new instance of Atomic Tx.
	 *
	 * @param ccc
	 * @param pirvateId
	 * @return
	 */
	public ActivityCoordinatorContext createAtActivity(CreateCoordinationContextType ccc, String pirvateId) {
		CoordinationContext sctx = ccc.getCurrentContext();
		ActivityCoordinatorContext activityCoordinatorContext;
		if (sctx != null){
			AtomicTxSubordinate subordinate = new AtomicTxSubordinate();
			subordinate.setSuperiorCtx(sctx);
			subordinate.setState(AtomicTxSubordinate.State.Active);
			activityCoordinatorContext = subordinate;
		}else{
			AtomicTxCoordinator atcoordinator = new AtomicTxCoordinator();
			atcoordinator.setState(AtomicTxCoordinator.State.Active);
			activityCoordinatorContext = atcoordinator;
		}
		activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
		if (ccc.getExpires() != null) {
			activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
		}
		activityCoordinatorContext.setPrivateId(pirvateId);
		return activityCoordinatorContext;
	}
}
