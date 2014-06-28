package info.fzhen.wstx.wsat;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

@WebService
public class Volatile2PcParticipantPort implements ParticipantPortType{

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
