package info.fzhen.wstx.ba.cc;

import info.fzhen.wstx.AbstractParticipantProtocolMgr;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.ba.BaProtocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

/**
 * Business activity Coordinator completion protocol
 * participant side manager
 */
public class CcParticipantProtocolMgr extends AbstractParticipantProtocolMgr<CcParticipantProtocolService> {
	private static final Log __LOG = LogFactory.getLog(CcParticipantProtocolMgr.class);

	private static CcParticipantProtocolMgr instance;

	/**
	 * register CC protocol participant
	 * @param ctx
	 * @param part
	 */
	public void registerBacc(CoordinationContext ctx, CcParticipant part) {
		CcParticipantProtocolService pps = new CcParticipantProtocolService();
		pps.setParticipant(part);
		String protocolId = BaProtocol.COORDINATOR_COMPLETION.getText();
		doRegister(ctx, pps, protocolId);
	}

	public static CcParticipantProtocolMgr getInstance() {
		if (instance == null) {
			String msg = "Business activity Coordinator completion protocol" +
						" participant Manager hasn't been initialized yet. " +
						"the manager instance should be initialized by Spring";
			if (__LOG.isErrorEnabled()) {
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}

	public static void setInstance(CcParticipantProtocolMgr i){
		instance = i;
	}
}
