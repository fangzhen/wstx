package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionCoordinatorProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCompletionCoordinatorPort implements BaCompletionCoordinatorPortType{
	@Resource
	private WebServiceContext wsContext;

	private CompletionCoordinatorProtocolMgr mgr;

	@Override
	public void completeOperation(@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") NotificationType parameters) {
		CompletionCoordinatorProtocolService coorService =
				MsgContextUtil.getTargetCoorService(mgr, wsContext);
		coorService.complete();
	}

	@Override
	public void cancleOperation(@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") NotificationType parameters) {

	}

	private CompletionCoordinatorProtocolMgr getMgr() {
		if (mgr == null){
			mgr = CompletionCoordinatorProtocolMgr.getInstance();
		}
		return mgr;
	}
}
