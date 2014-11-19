package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class BaCompletionInitiatorPort implements  BaCompletionInitiatorPortType{
	@Override
	public void completedOperation(@WebParam(name = "Committed", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") Notification parameters) {

	}

	@Override
	public void canceledOperation(@WebParam(name = "Aborted", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters") Notification parameters) {

	}
}
