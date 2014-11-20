package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.cc.CcParticipantProtocolMgr;
import info.fzhen.wstx.ba.cc.CcParticipantProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsba._2006._06.BusinessAgreementWithCoordinatorCompletionParticipantPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.StatusType;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCCParticipantPort implements BusinessAgreementWithCoordinatorCompletionParticipantPortType{
	@Resource
	private WebServiceContext wsContext;
	private CcParticipantProtocolMgr mgr = CcParticipantProtocolMgr.getInstance();

	@Override
	public void completeOperation(@WebParam(name = "Complete", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {
		CcParticipantProtocolService service = MsgContextUtil.getTargetPtcpService(mgr, wsContext);
		service.complete();
	}

	@Override
	public void closeOperation(@WebParam(name = "Close", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {
		CcParticipantProtocolService service = MsgContextUtil.getTargetPtcpService(mgr, wsContext);
		service.close();
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
