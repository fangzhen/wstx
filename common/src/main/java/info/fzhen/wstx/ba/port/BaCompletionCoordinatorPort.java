package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class BaCompletionCoordinatorPort implements BaCompletionCoordinatorPortType{
	@Override
	public void completeOperation(@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {

	}

	@Override
	public void cancleOperation(@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters") Notification parameters) {

	}
}
