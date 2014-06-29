package info.fzhen.wstx.wsat;

import info.fzhen.wstx.at.AtomicTxCoordinator;
import info.fzhen.wstx.context.ActivityCoordinatorContext;
import info.fzhen.wstx.coordinator.CoordinatorManager;
import info.fzhen.wstx.util.EprUtils;
import info.fzhen.wstx.util.MsgContextUtil;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionCoordinatorPort implements CompletionCoordinatorPortType{
	@Resource
	private WebServiceContext wsContext;
	
	@Override
	public void commitOperation(Notification parameters) {
		String txId = MsgContextUtil.retrievePrivateId(wsContext);
		ActivityCoordinatorContext activity = CoordinatorManager.getInstance().getActivity(txId);
		AtomicTxCoordinator at = null;
		try{
			at = (AtomicTxCoordinator)activity;
		}catch(ClassCastException e){
			//TODO illegal transaction
			e.printStackTrace();
			return;
		}
		EndpointReferenceType epr = at.getInitiatorEpr();
		CompletionInitiatorPortType initiator = EprUtils.createWsaddrClientProxy(CompletionInitiatorPortType.class, epr);
		initiator.committedOperation(new Notification());
	}

	@Override
	public void rollbackOperation(Notification parameters) {
				
	}
}
