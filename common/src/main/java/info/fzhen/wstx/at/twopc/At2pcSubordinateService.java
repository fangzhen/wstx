package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.at.AtomicTxSubordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;


public class At2pcSubordinateService {
	private static final Log __LOG = LogFactory.getLog(At2pcSubordinateService.class);

	public PartService partService = this.new PartService();
	public CoorService coorService = this.new CoorService();

	/**
	 * Protocol service of subordinate as participant
	 */
	class PartService extends PartService2Pc<At2pcParticipant, CoordinatorPortType>{

		@Override
		public void prepare() {
			coorService.prepare();
		}

		@Override
		public void commit() {
			coorService.commit();
		}

		public void prepared() {
			if (__LOG.isInfoEnabled()){
				__LOG.info("Subordinate received prepared from participant; " +
						"Forward it to coordinator");
			}
			CoordinatorPortType coordinatorProxy =
					getCoordinatorProxy(CoordinatorPortType.class);
			coordinatorProxy.preparedOperation(new Notification());
		}
		public void committed(){
			if (__LOG.isInfoEnabled()){
				__LOG.info("Subordinate received prepared from participant; " +
						"Forward it to coordinator");
			}
			CoordinatorPortType coordinatorProxy =
					getCoordinatorProxy(CoordinatorPortType.class);
			coordinatorProxy.committedOperation(new Notification());
		}
	}

	/**
	 * Protocol service of subordinate as coordinator
	 */
	class CoorService extends CoorService2Pc<ParticipantPortType, AtomicTxSubordinate>{

		public void prepare() {
			ParticipantPortType participantProxy =
					getParticipantProxy(ParticipantPortType.class);
			participantProxy.prepareOperation(new Notification());
			if (__LOG.isInfoEnabled()){
				__LOG.info("Subordinate sent prepare to participant " + participantEpr.getAddress().getValue());
			}
		}

		@Override
		public void prepared() {
			partService.prepared();
		}

		public void commit() {
			ParticipantPortType participantProxy =
					getParticipantProxy(ParticipantPortType.class);
			participantProxy.commitOperation(new Notification());
			if (__LOG.isInfoEnabled()){
				__LOG.info("Subordinate sent commit to participant " + participantEpr.getAddress().getValue());
			}
		}

		@Override
		public void committed() {
			partService.committed();
		}
	}
}
