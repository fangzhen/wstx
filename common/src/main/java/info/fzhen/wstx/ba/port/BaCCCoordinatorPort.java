package info.fzhen.wstx.ba.port;

import info.fzhen.wstx.ba.cc.CcCoordinatorProtocolMgr;
import info.fzhen.wstx.ba.cc.CcCoordinatorProtocolService;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsba._2006._06.BusinessAgreementWithCoordinatorCompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.ExceptionType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;
import org.oasis_open.docs.ws_tx.wsba._2006._06.StatusType;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class BaCCCoordinatorPort implements BusinessAgreementWithCoordinatorCompletionCoordinatorPortType{
	@Resource
	private WebServiceContext wsContext;

	private CcCoordinatorProtocolMgr mgr;

	@Override
	public void completedOperation(@WebParam(name = "Completed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {
		CcCoordinatorProtocolService service = MsgContextUtil.getTargetCoorService(getMgr(), wsContext);
		service.completed();
	}

	@Override
	public void failOperation(@WebParam(name = "Fail", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") ExceptionType parameters) {

	}

	@Override
	public void compensatedOperation(@WebParam(name = "Compensated", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {

	}

	@Override
	public void closedOperation(@WebParam(name = "Closed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters") NotificationType parameters) {
		CcCoordinatorProtocolService service = MsgContextUtil.getTargetCoorService(getMgr(), wsContext);
		service.closed();
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

	private CcCoordinatorProtocolMgr getMgr() {
		if (mgr == null){
			mgr = CcCoordinatorProtocolMgr.getInstance();
		}
		return mgr;
	}
}
