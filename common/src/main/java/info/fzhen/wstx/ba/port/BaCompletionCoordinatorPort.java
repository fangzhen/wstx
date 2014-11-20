package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCompletionCoordinatorPort implements BaCompletionCoordinatorPortType{
	@Resource
	private WebServiceContext wsContext;

	@Override
	public void completeOperation(@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {

	}

	@Override
	public void cancleOperation(@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {

	}

}
