package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractParticipantProtocolMgr;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.AtProtocol;
import info.fzhen.wstx.ba.WsBusinessActicity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * completion protocol manager of coordinator side
 */
public class CompletionParticipantProtocolMgr extends AbstractParticipantProtocolMgr<CompletionParticipantProtocolService> {
	private static final Log __LOG = LogFactory.getLog(CompletionParticipantProtocolMgr.class);

	private static CompletionParticipantProtocolMgr instance;

	public void reigsterInitiator(WsBusinessActicity acticity) {
		CompletionParticipantProtocolService initiator = new CompletionParticipantProtocolService();
		initiator.setActicity(acticity);
		acticity.setInitiator(initiator);
		doRegister(acticity.getCoordinationContext(), initiator, AtProtocol.COMPLETION.getText());
	}


	public static CompletionParticipantProtocolMgr getInstance() {
		if (instance == null) {
			String msg = "Business activity completion protocol" +
						" participant Manager hasn't been initialized yet. " +
						"the manager instance should be initialized by Spring";
			if (__LOG.isErrorEnabled()) {
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}
	public static void setInstance(CompletionParticipantProtocolMgr instance) {
		CompletionParticipantProtocolMgr.instance = instance;
	}
}
