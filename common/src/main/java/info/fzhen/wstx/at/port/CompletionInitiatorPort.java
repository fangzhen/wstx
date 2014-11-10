package info.fzhen.wstx.at.port;

import info.fzhen.wstx.at.completion.AtInitiatorPartManager;
import info.fzhen.wstx.at.completion.AtInitiatorPartService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class CompletionInitiatorPort implements CompletionInitiatorPortType {
	@Resource
	WebServiceContext wsContext;

	public CompletionInitiatorPort() {

	}

	@Override
	public void committedOperation(Notification parameters) {
		AtInitiatorPartService targetedParticipant = getTargetedParticipant();
		targetedParticipant.committed();
	}

	@Override
	public void abortedOperation(Notification parameters) {

	}

	private AtInitiatorPartService getTargetedParticipant() {
		String id = MsgContextUtil.retrievePrivateId(wsContext);
		AtInitiatorPartService targetedPart = AtInitiatorPartManager.getInstance().retrieveProtocolParticipant(id);
		return targetedPart;
	}
}
