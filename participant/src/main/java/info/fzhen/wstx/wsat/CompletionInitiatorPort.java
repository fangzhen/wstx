package info.fzhen.wstx.wsat;

import info.fzhen.wstx.participant.at.ATInitiator;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionInitiatorPort implements CompletionInitiatorPortType {
	private ATInitiator initiator;
//	@Resource
//	WebServiceContext wsContext;
	public CompletionInitiatorPort(){
		
	}
	//	private WsatTransaction transaction;
	@Override
	public void committedOperation(Notification parameters) {
	}

	@Override
	public void abortedOperation(Notification parameters) {

	}

	public ATInitiator getInitiator() {
		return initiator;
	}

	public void setInitiator(ATInitiator initiator) {
		this.initiator = initiator;
	}

	//	public WsatTransaction getTransaction() {
	//		return transaction;
	//	}
	//
	//	public void setTransaction(WsatTransaction transaction) {
	//		this.transaction = transaction;
	//	}

}
