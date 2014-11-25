package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCompletionInitiatorPort implements  BaCompletionInitiatorPortType{
	@Resource
	private WebServiceContext wsContext;
	private CompletionParticipantProtocolMgr mgr;

	@Override
	public void completedOperation(@WebParam(name = "Committed", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") NotificationType parameters) {
		CompletionParticipantProtocolService service = MsgContextUtil.getTargetPtcpService(getMgr(), wsContext);
		service.completed();
	}

	@Override
	public void canceledOperation(@WebParam(name = "Aborted", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") NotificationType parameters) {

	}

	private CompletionParticipantProtocolMgr getMgr() {
		if (mgr == null){
			mgr = CompletionParticipantProtocolMgr.getInstance();
		}
		return mgr;
	}
}
