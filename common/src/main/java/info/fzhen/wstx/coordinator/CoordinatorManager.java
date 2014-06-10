package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.at.ATActivityCoordinatorContext;
import info.fzhen.wstx.context.ActivityCoordinatorContext;
import java.util.Random;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
/**
 * Generalized coordinator, provide activation service and registration service.
 * @author fangzhen
 *
 */
public class CoordinatorManager {
	private static CoordinatorManager instance;
	
	public static CoordinatorManager getInstance() {
		return instance;
	}

	public static void setInstance(CoordinatorManager instance) {
		CoordinatorManager.instance = instance;
	}

	private CoordinatorContext coordinatorContext;

	public ActivityCoordinatorContext createActivityCoordinatorContext(CreateCoordinationContextType ccc){
		switch (ccc.getCoordinationType()) {
		case Constants.WSAT:
			ATActivityCoordinatorContext activityCoordinatorContext;
			activityCoordinatorContext = new ATActivityCoordinatorContext();
			activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
			if (ccc.getExpires() != null){
				activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
			}
			String shortId = getUniqueNum();
			activityCoordinatorContext.setIdentifier(shortId);
			activityCoordinatorContext.setCoordinatorContext(coordinatorContext);

			return activityCoordinatorContext;
		case Constants.WSBA:
			break;
		default:
			break;
		}
		return null;
	}

	private String getUniqueNum() {
		// TODO should ensure it to be unique. simply random
		return ""+new Random().nextInt();
	}

	public CoordinatorContext getCoordinatorContext() {
		return coordinatorContext;
	}

	public void setCoordinatorContext(CoordinatorContext coordinatorContext) {
		this.coordinatorContext = coordinatorContext;
	}

}
