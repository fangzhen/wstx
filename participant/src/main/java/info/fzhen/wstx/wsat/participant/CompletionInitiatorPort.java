package info.fzhen.wstx.wsat.participant;

import info.fzhen.wstx.at.participant.AtInitiatorPartService;
import info.fzhen.wstx.at.participant.AtInitiatorPartManager;
import info.fzhen.wstx.util.MsgContextUtil;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionInitiatorPort implements CompletionInitiatorPortType {
	private AtInitiatorPartService initiator;
	@Resource
	WebServiceContext wsContext;
	public CompletionInitiatorPort(){
		
	}

	@Override
	public void committedOperation(Notification parameters) {
		String txId = MsgContextUtil.retrievePrivateId(wsContext);
		System.out.println("transaction " + txId + "successfully committed");
		AtInitiatorPartManager.getInstance().forgetInitiator(txId);
	}

	@Override
	public void abortedOperation(Notification parameters) {

	}

	public AtInitiatorPartService getInitiator() {
		return initiator;
	}

	public void setInitiator(AtInitiatorPartService initiator) {
		this.initiator = initiator;
	}
}
