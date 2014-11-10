package info.fzhen.wstx.at.port;

import info.fzhen.wstx.at.twopc.At2pcPartManager;
import info.fzhen.wstx.at.twopc.PartService2Pc;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class twoPcParticipantPort implements ParticipantPortType {
	@Resource
	private WebServiceContext wsContext;

	@Override
	public void prepareOperation(Notification parameters) {
		PartService2Pc targetedPart = getTargetedParticipant();
		targetedPart.prepare();
	}

	@Override
	public void commitOperation(Notification parameters) {
		PartService2Pc targetedPart = getTargetedParticipant();
		targetedPart.commit();
	}

	@Override
	public void rollbackOperation(Notification parameters) {
		// TODO Auto-generated method stub

	}

	private PartService2Pc getTargetedParticipant() {
		String id = MsgContextUtil.retrievePrivateId(wsContext);
		PartService2Pc targetedPart = At2pcPartManager.getInstance().retrieveProtocolParticipant(id);
		return targetedPart;
	}
}
