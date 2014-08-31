package info.fzhen.wstx.wsat.coordinator;

import org.oasis_open.docs.ws_tx.wsat._2006._06.CoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

import javax.jws.WebService;

@WebService
public class AtCoordinatorPort implements CoordinatorPortType {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void committedOperation(Notification parameters) {
		// TODO Auto-generated method stub

	}

}
