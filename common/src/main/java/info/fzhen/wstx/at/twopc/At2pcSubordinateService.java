package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.at.AtomicTxSubordinate;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;


public class At2pcSubordinateService {
	public PartService partService = this.new PartService();
	public CoorService coorService = this.new CoorService();

	/**
	 * Protocol service of subordinate as participant
	 */
	class PartService extends PartService2Pc<At2pcParticipant, CoordinatorPortType>{

		@Override
		public void prepare() {

		}

		@Override
		public void commit() {

		}
	}

	/**
	 * Protocol service of subordinate as coordinator
	 */
	class CoorService extends CoorService2Pc<ParticipantPortType, AtomicTxSubordinate>{
		@Override
		public void prepare() {

		}

		@Override
		public void prepared() {

		}

		@Override
		public void commit() {

		}

		@Override
		public void committed() {

		}
	}
}
