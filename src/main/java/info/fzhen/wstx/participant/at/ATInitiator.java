package info.fzhen.wstx.participant.at;

import javax.xml.ws.EndpointReference;

import info.fzhen.wstx.participant.Participant;
/**
 * Participant of WSAT completion protocol.
 * @author fangzhen
 *
 */
public class ATInitiator implements Participant{
	private EndpointReference endpointReference;
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
}
