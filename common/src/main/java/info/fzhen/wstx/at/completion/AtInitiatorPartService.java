package info.fzhen.wstx.at.completion;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.WsatTransaction;
import info.fzhen.wstx.WsTransaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

/**
 * Participant service of WSAT completion protocol.
 *
 * @author fangzhen
 */
public class AtInitiatorPartService extends AbstractParticipantProtocolService
		<AtInitiatorParticipant, CompletionCoordinatorPortType, AtInitiatorPartService.State> {
	private static final Log __LOG = LogFactory.getLog(AtInitiatorPartService.class);

	WsatTransaction transaction;

	/**
	 * send a commit message to coordinator
	 */
	public void commit() {
		if (__LOG.isInfoEnabled()) {
			__LOG.info("The initiator sends commit message to coordinator");
		}
		CompletionCoordinatorPortType coorProxy = getCoordinatorProxy(CompletionCoordinatorPortType.class);
		coorProxy.commitOperation(new Notification());
		state = State.Completing;
	}

	public void committed() {
		transaction.committed();
	}

	public void aborted() {

	}

	public WsatTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(WsTransaction transaction) {
		if (!(transaction instanceof WsatTransaction))
			throw new WstxRtException("Attempted to set transaction of completion protocol participant to non-wsat transaction");
		this.transaction = (WsatTransaction) transaction;
	}

	public static enum State implements StateEnum {
		None,
		Active,
		Completing
	}
}
