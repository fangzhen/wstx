package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.coordinator.AtProtocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Global protocol service manager of the site. It also holds global context.
 * Used by initiator of Completion protocol participant side.
 *
 * @author fangzhen
 */
public class AtInitiatorPartManager extends AtAbstractPartManager<AtInitiatorPartService> {
	private static final Log __LOG = LogFactory.getLog(Process.class);

	private static AtInitiatorPartManager instance;

	/**
	 * Participants managed by this manager (on this site)
	 */
	Map<String, AtInitiatorPartService> initiators = new HashMap<String, AtInitiatorPartService>();

	public static AtInitiatorPartManager getInstance() {
		if (instance == null) {
			if (__LOG.isErrorEnabled()) {
				__LOG.error("Atomic Transaction Manager hasn't been initialized");
			}
			throw new WstxRtException("Atomic Transaction Manager hasn't been initialized");
		}
		return instance;
	}

	public static void setInstance(AtInitiatorPartManager instance) {
		AtInitiatorPartManager.instance = instance;
	}

	public void registerInitiator(WsatTransaction transaction) {
		AtInitiatorPartService initiator = new AtInitiatorPartService();
		initiator.setTransaction(transaction);
		transaction.setInitiator(initiator);

		doRegister(transaction.getCoordinationContext(), initiator, AtProtocol.COMPLETION.getText());
	}

	public AtInitiatorPartService getInitiator(String id) {
		return initiators.get(id);
	}

	public void forgetInitiator(String txId) {
		initiators.remove(txId);
	}
}
