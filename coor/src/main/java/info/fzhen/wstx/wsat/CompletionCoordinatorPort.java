package info.fzhen.wstx.wsat;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionCoordinatorPort implements CompletionCoordinatorPortType{

	@Override
	public void commitOperation(Notification parameters) {
	
	}

	@Override
	public void rollbackOperation(Notification parameters) {
				
	}
}
