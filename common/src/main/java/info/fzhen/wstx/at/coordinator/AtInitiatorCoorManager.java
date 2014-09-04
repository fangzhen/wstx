package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.util.CommonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * Coordiantor side initiator manager.
 */
public class AtInitiatorCoorManager extends AtAbstractCoorManager<AtInitiatorCoor> {
	private static final Log __LOG = LogFactory.getLog(AtInitiatorCoorManager.class);

	private static AtInitiatorCoorManager instance;

	public static AtInitiatorCoorManager getInstance() {
		if (instance == null) {
			if (__LOG.isErrorEnabled()) {
				__LOG.error("Initiator Manager(InitiatorCoorManager) " +
						"hasn't been initialized. It should be initiated " +
						"by Spring as singleton. Please check your conf file.");
			}
			throw new WstxRtException("Initiator Manager(InitiatorCoorManager) " +
					"hasn't been initialized. It should be initiated " +
					"by Spring as singleton. Please check your conf file.");
		}
		return instance;
	}

	public static void setInstance(AtInitiatorCoorManager instance) {
		AtInitiatorCoorManager.instance = instance;
	}

	public RegisterResponseType registerInitiator(AtomicTxCoordinator activity, RegisterType regPara) {
		AtInitiatorCoor initiatorCoor = new AtInitiatorCoor();
		initiatorCoor.setActivity(activity);
		activity.setInitiatorCoor(initiatorCoor);
		initiatorCoor.setParticipantEpr(regPara.getParticipantProtocolService());

		String privateId = CommonUtils.genPrivateId();
		managedCoordinators.put(privateId, initiatorCoor);

		//construct the response
		RegisterResponseType response = constructRegisterResponse(privateId);
		return response;
	}
}
