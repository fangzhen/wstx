package info.fzhen.wstx.wsat.participant;

import info.fzhen.wstx.at.participant.At2pcPartService;
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
        At2pcPartService targetedPart = getTargetedParticipant();
        targetedPart.prepare();
	}

	@Override
	public void commitOperation(Notification parameters) {
        At2pcPartService targetedPart = getTargetedParticipant();
        targetedPart.commit();
	}

	@Override
	public void rollbackOperation(Notification parameters) {
		// TODO Auto-generated method stub
		
	}

    private At2pcPartService getTargetedParticipant(){
        String id = MsgContextUtil.retrievePrivateId(wsContext);
        At2pcPartService targetedPart = At2pcPartManager.getInstance().get2PcParticipant(id);
        return targetedPart;
    }
}
