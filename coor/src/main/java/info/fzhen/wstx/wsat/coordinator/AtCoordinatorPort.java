package info.fzhen.wstx.wsat.coordinator;

import info.fzhen.wstx.at.twopc.At2pcCoor;
import info.fzhen.wstx.at.twopc.At2pcCoorManager;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class AtCoordinatorPort implements CoordinatorPortType {
	@Resource
	private WebServiceContext wsContext;

	@Override
	public void abortedOperation(Notification parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readOnlyOperation(Notification parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preparedOperation(Notification parameters) {
		At2pcCoor coorService = getTargetedCoordinator();
		coorService.prepared();
	}

	@Override
	public void committedOperation(Notification parameters) {
		At2pcCoor coorService = getTargetedCoordinator();
		coorService.committed();
	}

	private At2pcCoor getTargetedCoordinator() {
		String id = MsgContextUtil.retrievePrivateId(wsContext);
		At2pcCoor coorService = At2pcCoorManager.getInstance().retrieveProtocolCoordinator(id);
		return coorService;
	}
}
