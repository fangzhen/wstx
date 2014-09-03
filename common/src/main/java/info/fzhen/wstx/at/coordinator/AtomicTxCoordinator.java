package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.context.AbstractActivityCoordinatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
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
    private List<At2pcCoor> d2pcCoors = new ArrayList<>();
    private List<At2pcCoor> v2pcCoors = new ArrayList<>();

    /** number of participant of each state. see {@link info.fzhen.wstx.at.coordinator.At2pcCoor.State} */
    private int[] volatileNum = new int[10];
    private int[] durableNum = new int[10];

    /** state of the activity */
    private State state;

    /**
     * Factory method that create new instance of Atomic Tx.
     *
     * @param ccc
     * @param pirvateId
     * @return
     */
    public static AtomicTxCoordinator createInstance(CreateCoordinationContextType ccc, String pirvateId) {
        AtomicTxCoordinator activityCoordinatorContext = new AtomicTxCoordinator();
        activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
        if (ccc.getExpires() != null) {
            activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
        }
        activityCoordinatorContext.setPrivateId(pirvateId);
        activityCoordinatorContext.state = State.Active;
        return activityCoordinatorContext;
    }

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
    public void commitActivity(){
        synchronized (state){
            try {
                while(state != State.AllCommitted) {
                    state.wait();
                }
            } catch (InterruptedException e) {
                //TODO handle this
                if (__LOG.isWarnEnabled()){
                    __LOG.warn("interrupted during commit the activity: " + this);
                }
            }
        }
        prepareVolatile2PC();
    }

    /**
     * start to prepare volatile 2PC.
     */
    public void prepareVolatile2PC() {
        synchronized (state) {
            synchronized (v2pcCoors) {
                synchronized (volatileNum) {
                    state = State.VolatilePraparing;
                    for (At2pcCoor coor2pc : v2pcCoors) {
                        coor2pc.prepare();
                        volatileNum[At2pcCoor.State.Active.getId()]--;
                        volatileNum[At2pcCoor.State.Prepared.getId()]++;
                    }
                }
            }
        }
    }

    public void preparedVolatile2PC() {
        synchronized (state) {
            synchronized (volatileNum) {
                volatileNum[At2pcCoor.State.Preparing.getId()]--;
                volatileNum[At2pcCoor.State.Prepared.getId()]++;
                if (volatileNum[At2pcCoor.State.Preparing.getId()] == 0) {
                    state = State.VolatileAllPrepared;
                    prepareDurable2PC();
                }
            }
        }
    }

    private void prepareDurable2PC() {
        synchronized (state) {
            synchronized (d2pcCoors) {
                synchronized (durableNum) {
                    state = State.DurablePreparing;
                    for (At2pcCoor d2pc : d2pcCoors) {
                        d2pc.prepare();
                        durableNum[At2pcCoor.State.Active.getId()]--;
                        durableNum[At2pcCoor.State.Prepared.getId()]++;
                    }
                }
            }
        }
    }

    public void preparedDurable2PC() {
        synchronized (state){
            synchronized (durableNum){
                durableNum[At2pcCoor.State.Preparing.getId()]--;
                durableNum[At2pcCoor.State.Prepared.getId()]++;
                if (durableNum[At2pcCoor.State.Preparing.getId()] == 0){
                    state = State.DurablePrePared;
                    commitPhase();
                }
            }
        }
    }

    /** Phase 2: commit phase*/
    private void commitPhase() {
        synchronized (state){
            state = State.Committing;
            synchronized (v2pcCoors) {
                synchronized (volatileNum) {
                    for (At2pcCoor v2pc : v2pcCoors){
                        v2pc.commit();
                        volatileNum[At2pcCoor.State.Prepared.getId()] --;
                        volatileNum[At2pcCoor.State.Committing.getId()] ++;
                    }
                }
            }

            synchronized(d2pcCoors){
                synchronized (durableNum){
                    for (At2pcCoor d2pc : d2pcCoors){
                        d2pc.commit();
                        durableNum[At2pcCoor.State.Prepared.getId()] --;
                        durableNum[At2pcCoor.State.Committing.getId()] ++;
                    }
                }
            }
        }
    }

    public void CommittedVolatile2PC(){
        synchronized (state){
            synchronized (volatileNum) {
                if (state == State.Committing) {
                    volatileNum[At2pcCoor.State.Committing.getId()]--;
                    if (volatileNum[At2pcCoor.State.Committing.getId()] == 0) {
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
        synchronized (state){
            synchronized (durableNum) {
                if (state == State.Committing) {
                    durableNum[At2pcCoor.State.Committing.getId()]--;
                    if (durableNum[At2pcCoor.State.Committing.getId()] == 0) {
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
        synchronized (state) {
            state = State.AllCommitted;
            state.notifyAll();
        }
    }

    public AtInitiatorCoor getInitiatorCoor() {
        return initiatorCoor;
    }

    public void setInitiatorCoor(AtInitiatorCoor initiatorCoor) {
        this.initiatorCoor = initiatorCoor;
    }

    public void addD2pcCoor(At2pcCoor d2pcCoor) {
        synchronized(d2pcCoors){
            d2pcCoors.add(d2pcCoor);
        }
        synchronized (durableNum){
            durableNum[At2pcCoor.State.Active.getId()] ++;
        }
    }

    public void addV2pcCoor(At2pcCoor v2pcCoor) {
        synchronized (v2pcCoors){
            v2pcCoors.add(v2pcCoor);
        }
        synchronized (volatileNum){
            volatileNum[At2pcCoor.State.Active.getId()] ++;
        }
    }

    /**
     * States of the activity
     */
    public static enum State {
        None,
        Active,
        VolatilePraparing,
        VolatileAllPrepared,
        DurablePreparing,
        DurablePrePared,
        PreparedSuccess,
        Committing,
        VolatileAllCommitted,
        DurableAllCommitted,
        AllCommitted,
        Aborting
    }
}