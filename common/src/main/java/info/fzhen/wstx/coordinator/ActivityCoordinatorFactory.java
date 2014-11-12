package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.at.AtomicTxCoordinator;
import info.fzhen.wstx.at.AtomicTxSubordinate;
import info.fzhen.wstx.ba.BaCoordinator;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

public class ActivityCoordinatorFactory {
	/**
	 * Factory method that create new instance of Atomic Tx.
	 *
	 * @param ccc
	 * @param privateId
	 * @return
	 */
	public static ActivityCoordinatorContext createAtActivity(CreateCoordinationContextType ccc, String privateId) {
		ActivityCoordinatorContext activityCoordinatorContext;
		AtomicTxCoordinator atcoordinator = new AtomicTxCoordinator();
		atcoordinator.setState(AtomicTxCoordinator.State.Active);
		activityCoordinatorContext = atcoordinator;
		activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
		if (ccc.getExpires() != null) {
			activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
		}
		activityCoordinatorContext.setPrivateId(privateId);
		return activityCoordinatorContext;
	}

	/**
	 * create business activity
	 * @param ccc
	 * @param privateId
	 * @return
	 */
	public static ActivityCoordinatorContext createBusinessActivity(CreateCoordinationContextType ccc, String privateId) {
		BaCoordinator coordinator;
		coordinator = new BaCoordinator();
		coordinator.setCoordinationType(ccc.getCoordinationType());
		if (ccc.getExpires() != null){
			coordinator.setExpires(ccc.getExpires().getValue());
		}
		coordinator.setPrivateId(privateId);
		coordinator.setState(BaCoordinator.State.Active);
		return coordinator;
	}

	public static ActivityCoordinatorContext createAtSubActivity(CreateCoordinationContextType ccc, String privateId) {
		ActivityCoordinatorContext activityCoordinatorContext;
		CoordinationContext sctx = ccc.getCurrentContext();
		if (sctx != null) {
			AtomicTxSubordinate subordinate = new AtomicTxSubordinate();
			subordinate.setSuperiorCtx(sctx);
			subordinate.setState(AtomicTxSubordinate.State.Active);
			activityCoordinatorContext = subordinate;
			activityCoordinatorContext.setCoordinationType(sctx.getCoordinationType());
			if (sctx.getExpires() != null) {
				activityCoordinatorContext.setExpires(sctx.getExpires().getValue());
			}
			activityCoordinatorContext.setPrivateId(privateId);

		}else{
			//fallback to normal activity
			activityCoordinatorContext = createAtActivity(ccc, privateId);
		}
		return activityCoordinatorContext;
	}
}
