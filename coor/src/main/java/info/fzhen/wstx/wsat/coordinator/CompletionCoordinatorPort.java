package info.fzhen.wstx.wsat.coordinator;

import info.fzhen.wstx.at.coordinator.AtInitiatorCoor;
import info.fzhen.wstx.at.coordinator.AtInitiatorCoorManager;
import info.fzhen.wstx.at.coordinator.AtomicTxCoordinator;
import info.fzhen.wstx.util.EprUtils;
import info.fzhen.wstx.util.MsgContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class CompletionCoordinatorPort implements CompletionCoordinatorPortType{
    private static final Log __LOG = LogFactory.getLog(CompletionCoordinatorPort.class);

	@Resource
	private WebServiceContext wsContext;
	
	@Override
	public void commitOperation(Notification parameters) {
		String id = MsgContextUtil.retrievePrivateId(wsContext);
        AtInitiatorCoor initiatorCoor = AtInitiatorCoorManager.getInstance().retrieveProtocolCoordinator(id);
        if (initiatorCoor == null){
            if (__LOG.isErrorEnabled()){
                __LOG.error("Failed to get initiator protocol service with id: " + id);
            }
            return;
        }
		AtomicTxCoordinator activity = initiatorCoor.getActivity();
		activity.commit();

        //committed
		EndpointReferenceType epr = activity.getInitiatorCoor().getParticipantEpr();
		CompletionInitiatorPortType initiator = EprUtils.createWsaddrClientProxy(CompletionInitiatorPortType.class, epr);
		initiator.committedOperation(new Notification());
	}

	@Override
	public void rollbackOperation(Notification parameters) {
				
	}
}
