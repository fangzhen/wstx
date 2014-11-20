package info.fzhen.wstx.at.port;

import info.fzhen.wstx.at.completion.AtInitiatorCoor;
import info.fzhen.wstx.at.completion.AtInitiatorCoorManager;
import info.fzhen.wstx.util.MsgContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class CompletionCoordinatorPort implements CompletionCoordinatorPortType {
	private static final Log __LOG = LogFactory.getLog(CompletionCoordinatorPort.class);

	@Resource
	private WebServiceContext wsContext;

	@Override
	public void commitOperation(Notification parameters) {
		AtInitiatorCoor initiatorCoor = getTargetedCoordinator();
		if (initiatorCoor == null) {
			return;
		}
		initiatorCoor.commit();
	}

	@Override
	public void rollbackOperation(Notification parameters) {

	}

	private AtInitiatorCoor getTargetedCoordinator() {
		return MsgContextUtil.getTargetCoorService(AtInitiatorCoorManager.getInstance(), wsContext);
	}
}
