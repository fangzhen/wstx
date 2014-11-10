package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsba._2006._06.*;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class BaPCCoordinatorPort implements BusinessAgreementWithParticipantCompletionCoordinatorPortType{

	@Override
	public void completedOperation(@WebParam(name = "Completed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void failOperation(@WebParam(name = "Fail", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") ExceptionType parameters) {

	}

	@Override
	public void compensatedOperation(@WebParam(name = "Compensated", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void closedOperation(@WebParam(name = "Closed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void canceledOperation(@WebParam(name = "Canceled", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void exitOperation(@WebParam(name = "Exit", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void cannotComplete(@WebParam(name = "CannotComplete", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void getStatusOperation(@WebParam(name = "GetStatus", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void statusOperation(@WebParam(name = "Status", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") StatusType parameters) {

	}
}
