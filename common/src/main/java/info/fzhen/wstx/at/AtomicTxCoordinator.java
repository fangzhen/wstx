package info.fzhen.wstx.at;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.at.completion.*;
import info.fzhen.wstx.at.twopc.At2pcCoorService;
import info.fzhen.wstx.at.twopc.At2pcCoorManager;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

import java.util.ArrayList;
import java.util.List;

/**
 * Atomic transaction coordinator context instance.
 *
 * @author fangzhen
 */
public class AtomicTxCoordinator extends AbstractActivityCoordinatorContext {
	private static final Log __LOG = LogFactory.getLog(AtomicTxCoordinator.class);

	/**
	 * Protocol services of this activity
	 */
	private AtInitiatorCoor initiatorCoor;
	private final List<At2pcCoorService> d2pcCoors = new ArrayList<>();
	private final List<At2pcCoorService> v2pcCoors = new ArrayList<>();

	/**
	 * number of participant of each state. see {@link info.fzhen.wstx.at.twopc.At2pcCoorService.State}
	 */
	private final int[] volatileNum = new int[10];
	private final int[] durableNum = new int[10];

	/**
	 * state of the activity
	 */
	private State state;
	private final Object stateLock = new Object();

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		AtProtocol protocol = AtProtocol.fromString(protocolId);
		RegisterResponseType response = null;
		switch (protocol) {
			case COMPLETION:
				response = AtInitiatorCoorManager.getInstance().registerInitiator(this, registerPara);
				break;
			case DURABLE2PC:
				response = At2pcCoorManager.getInstance().registerD2pcParticipant(this, registerPara);
				break;
			case VOLATILE2PC:
				response = At2pcCoorManager.getInstance().registerV2pcParticipant(this, registerPara);
				break;
		}
		return response;
	}

	/**
	 * commit the activity synchronously
	 */
	public void commitActivity() {
		//TODO should give different result
		synchronized (stateLock) {
			prepareVolatile2PC();
			try {
				while (state != State.AllCommitted) {
					stateLock.wait();
				}
			} catch (InterruptedException e) {
				//TODO handle this
				if (__LOG.isWarnEnabled()) {
					__LOG.warn("interrupted during commit the activity: " + this);
				}
			}
		}
	}

	/**
	 * start to prepare volatile 2PC.
	 */
	public void prepareVolatile2PC() {
		synchronized (stateLock) {
			synchronized (v2pcCoors) {
				synchronized (volatileNum) {
					if (v2pcCoors.size() == 0) {
						state = State.VolatileAllPrepared;
						prepareDurable2PC();
					} else {
						state = State.VolatilePraparing;
						for (At2pcCoorService coor2pc : v2pcCoors) {
							coor2pc.prepare();
							volatileNum[At2pcCoorService.State.Active.getId()]--;
							volatileNum[At2pcCoorService.State.Preparing.getId()]++;
						}
					}
				}
			}
		}
	}

	public void preparedVolatile2PC() {
		synchronized (stateLock) {
			synchronized (volatileNum) {
				volatileNum[At2pcCoorService.State.Preparing.getId()]--;
				volatileNum[At2pcCoorService.State.Prepared.getId()]++;
				if (volatileNum[At2pcCoorService.State.Preparing.getId()] == 0) {
					state = State.VolatileAllPrepared;
					prepareDurable2PC();
				}
			}
		}
	}

	private void prepareDurable2PC() {
		synchronized (stateLock) {
			synchronized (d2pcCoors) {
				synchronized (durableNum) {
					if (d2pcCoors.size() == 0) {
						state = State.DurableAllPrepared;
						commitPhase();
					} else {
						state = State.DurablePreparing;
						for (At2pcCoorService d2pc : d2pcCoors) {
							d2pc.prepare();
							durableNum[At2pcCoorService.State.Active.getId()]--;
							durableNum[At2pcCoorService.State.Preparing.getId()]++;
						}
					}
				}
			}
		}
	}

	public void preparedDurable2PC() {
		synchronized (stateLock) {
			synchronized (durableNum) {
				durableNum[At2pcCoorService.State.Preparing.getId()]--;
				durableNum[At2pcCoorService.State.Prepared.getId()]++;
				if (durableNum[At2pcCoorService.State.Preparing.getId()] == 0) {
					state = State.DurableAllPrepared;
					commitPhase();
				}
			}
		}
	}

	/**
	 * Phase 2: commit phase
	 */
	private void commitPhase() {
		synchronized (stateLock) {
			state = State.Committing;
			synchronized (v2pcCoors) {
				synchronized (volatileNum) {
					if (v2pcCoors.size() == 0) {
						state = State.VolatileAllCommitted;
					} else {
						for (At2pcCoorService v2pc : v2pcCoors) {
							v2pc.commit();
							volatileNum[At2pcCoorService.State.Prepared.getId()]--;
							volatileNum[At2pcCoorService.State.Committing.getId()]++;
						}
					}
				}
			}

			synchronized (d2pcCoors) {
				synchronized (durableNum) {
					if (d2pcCoors.size() == 0) {
						if (state == State.VolatileAllCommitted) {
							completeActivity();
						} else {
							state = State.DurableAllCommitted;
						}
					} else {
						for (At2pcCoorService d2pc : d2pcCoors) {
							d2pc.commit();
							durableNum[At2pcCoorService.State.Prepared.getId()]--;
							durableNum[At2pcCoorService.State.Committing.getId()]++;
						}
					}
				}
			}
		}
	}

	public void CommittedVolatile2PC() {
		synchronized (stateLock) {
			synchronized (volatileNum) {
				if (state == State.Committing || state == State.DurableAllCommitted) {
					volatileNum[At2pcCoorService.State.Committing.getId()]--;
					if (volatileNum[At2pcCoorService.State.Committing.getId()] == 0) {
						if (state == State.DurableAllCommitted) {
							completeActivity();
						} else {
							state = State.VolatileAllCommitted;
						}
					}
				}
			}
		}
	}

	public void CommittedDurable2PC() {
		synchronized (stateLock) {
			synchronized (durableNum) {
				if (state == State.Committing || state == State.VolatileAllCommitted) {
					durableNum[At2pcCoorService.State.Committing.getId()]--;
					if (durableNum[At2pcCoorService.State.Committing.getId()] == 0) {
						if (state == State.VolatileAllCommitted) {
							completeActivity();
						} else {
							state = State.DurableAllCommitted;
						}
					}
				}
			}
		}
	}

	private void completeActivity() {
		synchronized (stateLock) {
			state = State.AllCommitted;
			stateLock.notifyAll();
		}
	}

	public AtInitiatorCoor getInitiatorCoor() {
		return initiatorCoor;
	}

	public void setInitiatorCoor(AtInitiatorCoor initiatorCoor) {
		this.initiatorCoor = initiatorCoor;
	}

	public void addD2pcCoor(At2pcCoorService d2pcCoor) {
		synchronized (d2pcCoors) {
			d2pcCoors.add(d2pcCoor);
		}
		synchronized (durableNum) {
			durableNum[At2pcCoorService.State.Active.getId()]++;
		}
	}

	public void addV2pcCoor(At2pcCoorService v2pcCoor) {
		synchronized (v2pcCoors) {
			v2pcCoors.add(v2pcCoor);
		}
		synchronized (volatileNum) {
			volatileNum[At2pcCoorService.State.Active.getId()]++;
		}
	}

	public void setState(State state) {
		this.state = state;
	}

	/**
	 * States of the activity
	 */
	public static enum State implements StateEnum{
		None,
		Active,
		VolatilePraparing,
		VolatileAllPrepared,
		DurablePreparing,
		DurableAllPrepared,
		PreparedSuccess,
		Committing,
		VolatileAllCommitted,
		DurableAllCommitted,
		AllCommitted,
		Aborting
	}
}