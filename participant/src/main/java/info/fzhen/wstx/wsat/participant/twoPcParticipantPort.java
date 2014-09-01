package info.fzhen.wstx.wsat.participant;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

import javax.jws.WebService;

@WebService
public class twoPcParticipantPort implements ParticipantPortType{

	@Override
	public void prepareOperation(Notification parameters) {
		// TODO Auto-generated method stub
		
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
