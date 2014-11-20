package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCompletionCoordinatorPort implements BaCompletionCoordinatorPortType{
	@Resource
	private WebServiceContext wsContext;

	private CompletionCoordinatorProtocolMgr mgr = CompletionCoordinatorProtocolMgr.getInstance();

	@Override
	public void completeOperation(@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {
		CompletionCoordinatorProtocolService coorService =
				MsgContextUtil.getTargetCoorService(mgr, wsContext);
		coorService.complete();
	}

	@Override
	public void cancleOperation(@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {

	}

}
