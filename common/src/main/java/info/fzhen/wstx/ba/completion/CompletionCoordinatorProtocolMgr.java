package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractCoordinatorProtocolMgr;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.ba.BaCoordinator;
import info.fzhen.wstx.util.CommonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * completion protocol manager of coordinator side
 */
public class CompletionCoordinatorProtocolMgr extends AbstractCoordinatorProtocolMgr<CompletionCoordinatorProtocolService>{
	private static final Log __LOG = LogFactory.getLog(CompletionParticipantProtocolMgr.class);
	private static CompletionCoordinatorProtocolMgr instance;

	public RegisterResponseType registerInitiator(BaCoordinator activity, RegisterType registerPara) {
		CompletionCoordinatorProtocolService completionCoordinator
				= new CompletionCoordinatorProtocolService();
		completionCoordinator.setActivity(activity);
		activity.setCompletionCoor(completionCoordinator);
		completionCoordinator.setParticipantEpr(
				registerPara.getParticipantProtocolService());

		String privateId = CommonUtils.genPrivateId();
		managedCoordinators.put(privateId, completionCoordinator);

		RegisterResponseType response = constructRegisterResponse(privateId);
		return response;
	}

	public static CompletionCoordinatorProtocolMgr getInstance() {
		if (instance == null) {
			String msg = "Business activity completion protocol" +
						" coordinator side Manager hasn't been initialized yet. " +
						"the manager instance should be initialized by Spring";
			if (__LOG.isErrorEnabled()) {
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}

	public static void setInstance(CompletionCoordinatorProtocolMgr instance) {
		CompletionCoordinatorProtocolMgr.instance = instance;
	}

}
