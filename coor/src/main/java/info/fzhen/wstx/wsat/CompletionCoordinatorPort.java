package info.fzhen.wstx.wsat;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

@WebService
public class CompletionCoordinatorPort implements CompletionInitiatorPortType{

	@Override
	public void committedOperation(Notification parameters) {
		
	}

	@Override
	public void abortedOperation(Notification parameters) {
		
	}

}
