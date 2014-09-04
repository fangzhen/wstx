package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.coordinator.AtProtocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

/**
 * Participant side 2PC participant manager. This manager is
 * singleton to the site which manages all the participants.
 */
public class At2pcPartManager extends AtAbstractPartManager<At2pcPartService> {
	private static final Log __LOG = LogFactory.getLog(AtAbstractPartManager.class);

	private static At2pcPartManager instance;

	public static At2pcPartManager getInstance() {
		if (instance == null) {
			if (__LOG.isErrorEnabled()) {
				__LOG.error("2PC participant Manager hasn't been initialized" +
						"the manager instance should be initialized by Spring");
			}
			throw new WstxRtException("2PC participant Manager hasn't been initialized" +
					"the manager instance should be initialized by Spring");
		}
		return instance;
	}

	public static void setInstance(At2pcPartManager instance) {
		At2pcPartManager.instance = instance;
	}

	/**
	 * register durable 2PC protocol
	 *
	 * @param coorContext transaction coordination context
	 * @param participant the participant to be registered.
	 */
	public void registerDurable2Pc(CoordinationContext coorContext, At2pcParticipant participant) {
		register2Pc(coorContext, participant, false);
	}

	public void registerVolatile2Pc(CoordinationContext coorContext, At2pcParticipant participant) {
		register2Pc(coorContext, participant, true);
	}

	/**
	 * @param coorContext
	 * @param participant
	 * @param isVolatile  is volatile 2PC or not (durable 2PC)
	 */
	private void register2Pc(CoordinationContext coorContext, At2pcParticipant participant, boolean isVolatile) {
		String protocolId;
		At2pcPartService participantSer = new At2pcPartService();
		participantSer.setParticipant(participant);
		if (isVolatile) {
			protocolId = AtProtocol.VOLATILE2PC.getText();
		} else {
			protocolId = AtProtocol.DURABLE2PC.getText();
		}
		participantSer.setState(At2pcPartService.State.Active);
		doRegister(coorContext, participantSer, protocolId);
	}
}
