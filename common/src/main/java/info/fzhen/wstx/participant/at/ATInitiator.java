package info.fzhen.wstx.participant.at;

import javax.xml.ws.EndpointReference;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.participant.Participant;
import info.fzhen.wstx.transaction.WsTransaction;
import info.fzhen.wstx.transaction.WsatTransaction;
/**
 * Participant of WSAT completion protocol.
 * @author fangzhen
 *
 */
public class ATInitiator implements Participant{
	private EndpointReference endpointReference;
	WsatTransaction transaction;
//	public ATInitiator(WsatTransaction transaction) {
//		setTransaction(transaction);
//	}

	public ATInitiator() {
		// TODO Auto-generated constructor stub
	}
	public void committed(){
		
	}
	public void aborted(){
		
	}

	public EndpointReference getEndpointReference() {
		return endpointReference;
	}
	public void setEndpointReference(EndpointReference endpointReference) {
		this.endpointReference = endpointReference;
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
