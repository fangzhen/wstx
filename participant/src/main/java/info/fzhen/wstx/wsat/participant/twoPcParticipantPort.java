package info.fzhen.wstx.wsat.participant;

import info.fzhen.wstx.at.participant.At2pcPart;
import info.fzhen.wstx.at.participant.At2pcPartManager;
import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class twoPcParticipantPort implements ParticipantPortType{
    @Resource
    private WebServiceContext wsContext;

	@Override
	public void prepareOperation(Notification parameters) {
        String id = MsgContextUtil.retrievePrivateId(wsContext);
        At2pcPart targetedPart = At2pcPartManager.getInstance().get2PcParticipant(id);
        targetedPart.prepare();
	}

	@Override
	public void commitOperation(Notification parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollbackOperation(Notification parameters) {
		// TODO Auto-generated method stub
		
	}

}
