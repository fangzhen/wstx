package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCompletionInitiatorPort implements  BaCompletionInitiatorPortType{
	@Resource
	private WebServiceContext wsContext;
	private CompletionParticipantProtocolMgr mgr = CompletionParticipantProtocolMgr.getInstance();

	@Override
	public void completedOperation(@WebParam(name = "Committed", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") Notification parameters) {
		CompletionParticipantProtocolService service = MsgContextUtil.getTargetPtcpService(mgr, wsContext);
		service.completed();
	}

	@Override
	public void canceledOperation(@WebParam(name = "Aborted", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") Notification parameters) {

	}
}
