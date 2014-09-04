package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.StateEnum;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;

public class At2pcPartService extends AtProtocolServicePart<At2pcParticipant, CoordinatorPortType> {
	public void prepare() {
		if (state == State.Active) {
			Vote vote = participant.prepare();
			CoordinatorPortType coorProxy = getCoordinatorProxy(CoordinatorPortType.class);
			switch (vote) {
				case Prepared:
					coorProxy.preparedOperation(new Notification());
					state = State.Prepared;
					break;
				case Aborted:
					//TODO rollback stuffs...
					coorProxy.abortedOperation(new Notification());
					state = State.None;
					break;
				case ReadOnly:
					coorProxy.readOnlyOperation(new Notification());
					state = State.None;
					break;
				default:
					//TODO invalid state
					break;
			}
		} else {
			//TODO other states
		}
	}

	public void commit() {
		if (state == State.Prepared) {
			participant.commit();
			CoordinatorPortType coorProxy = getCoordinatorProxy(CoordinatorPortType.class);
			coorProxy.committedOperation(new Notification());
			//TODO state? forget the participant?
		} else {
			// TODO other states
		}
	}

	public static enum State implements StateEnum {
		None,
		Active,
		Preparing,
		Prepared,
		PrepareSuccess,
		Committing
	}

	public static enum Vote {
		Prepared,
		Aborted,
		ReadOnly
	}
}
