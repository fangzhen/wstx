package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsba._2006._06.*;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class BaCCParticipantPort implements BusinessAgreementWithCoordinatorCompletionParticipantPortType{

	@Override
	public void completeOperation(@WebParam(name = "Complete", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void closeOperation(@WebParam(name = "Close", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void cancelOperation(@WebParam(name = "Cancel", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void compensateOperation(@WebParam(name = "Compensate", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void failedOperation(@WebParam(name = "Failed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void exitedOperation(@WebParam(name = "Exited", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void notCompleted(@WebParam(name = "NotCompleted", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void getStatusOperation(@WebParam(name = "GetStatus", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void statusOperation(@WebParam(name = "Status", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") StatusType parameters) {

	}
}
