package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.context.ActivityCoordinatorContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * Coordination registration service
 *
 * @author fangzhen
 */
public class RegistrationService {
	private static CoordinatorManager coordinator = CoordinatorManager.getInstance();

	public RegisterResponseType registerOperation(RegisterType registerPara, String targetId) {
		ActivityCoordinatorContext activity = coordinator.getActivity(targetId);
		return activity.register(registerPara);
	}
}
