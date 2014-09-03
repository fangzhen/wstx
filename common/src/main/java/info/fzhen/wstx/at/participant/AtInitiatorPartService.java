package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.WstxRtException;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;

/**
 * Participant service of WSAT completion protocol.
 * @author fangzhen
 *
 */
public class AtInitiatorPartService extends AtProtocolServicePart<AtInitiatorParticipant, CompletionCoordinatorPortType>{
	WsatTransaction transaction;

	public void committed(){
		
	}
	public void aborted(){
		
	}

	public void setTransaction(WsTransaction transaction) {
		if (!(transaction instanceof WsatTransaction))
			throw new WstxRtException("Attempted to set transaction of completion protocol participant to non-wsat transaction");
		this.transaction = (WsatTransaction)transaction;
	}
	public WsatTransaction getTransaction() {
		return transaction;
	}
}
