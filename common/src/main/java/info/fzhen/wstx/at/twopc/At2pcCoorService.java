package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.at.AtomicTxCoordinator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

/**
 * Protocol service of coordinator in AT 2PC protocol
 */
public class At2pcCoorService extends CoorService2Pc
		<ParticipantPortType, AtomicTxCoordinator, At2pcCoorService.State>{
	private static Log __LOG = LogFactory.getLog(At2pcCoorService.class);
	boolean isVolatile;

	public void prepare() {
		if (state == State.Active) {
			if (__LOG.isInfoEnabled()) {
				__LOG.info("Prepare participant " + participantEpr.getAddress().getValue());
			}
			ParticipantPortType partProxy = getParticipantProxy(ParticipantPortType.class);
			partProxy.prepareOperation(new Notification());
			state = State.Preparing;
		} else {
			//TODO handle other states
		}
	}

	@Override
	public void prepared() {
		if (state == State.Preparing) {
			if (__LOG.isInfoEnabled()) {
				__LOG.info("Prepared participant " + participantEpr.getAddress().getValue());
			}
			state = State.Prepared;
			if (isVolatile) {
				getActivity().preparedVolatile2PC();
			} else {
				getActivity().preparedDurable2PC();
			}
		}
	}

	/**
	 * commit phase
	 */
	public void commit() {
		if (state == State.Prepared) {
			if (__LOG.isInfoEnabled()) {
				__LOG.info("Committing participant " + participantEpr.getAddress().getValue());
			}
			state = State.Committing;
			ParticipantPortType partProxy = getParticipantProxy(ParticipantPortType.class);
			partProxy.commitOperation(new Notification());
		}
	}

	@Override
	public void committed() {
		if (state == State.Committing) {
			if (__LOG.isInfoEnabled()) {
				__LOG.info("committed participant" + participantEpr.getAddress().getValue());
			}
			if (isVolatile) {
				getActivity().CommittedVolatile2PC();
			} else {
				getActivity().CommittedDurable2PC();
			}
		}
	}

	public boolean isVolatile() {
		return isVolatile;
	}

	public void setVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

	/**
	 * states of coordinator of 2PC protocol. Each state is bind with a integer (0 ... 6)
	 * which can be retrieved by {@link #getId()}
	 */
	public static enum State implements StateEnum {
		None(0),
		Active(1),
		Preparing(2),
		Prepared(3),
		PrepareSuccess(4),
		Committing(5),
		Aborting(6);

		private int id;

		private State(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}
}
