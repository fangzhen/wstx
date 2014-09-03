package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.WstxRtException;

/**
 * Participant service of WSAT completion protocol.
 * @author fangzhen
 *
 */
public class AtInitiatorPartService extends AtProtocolServicePart<AtInitiatorParticipant>{
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
