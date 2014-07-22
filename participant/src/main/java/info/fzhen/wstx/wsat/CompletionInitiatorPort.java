package info.fzhen.wstx.wsat;

import info.fzhen.wstx.participant.at.ATInitiator;
import info.fzhen.wstx.transaction.WsatTxManager;
import info.fzhen.wstx.util.MsgContextUtil;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionInitiatorPort implements CompletionInitiatorPortType {
	private ATInitiator initiator;
	@Resource
	WebServiceContext wsContext;
	public CompletionInitiatorPort(){
		
	}

	@Override
	public void committedOperation(Notification parameters) {
		String txId = MsgContextUtil.retrievePrivateId(wsContext);
		System.out.println("transaction " + txId + "successfully committed");
		WsatTxManager.getInstance().forgetInitiator(txId);
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
}
