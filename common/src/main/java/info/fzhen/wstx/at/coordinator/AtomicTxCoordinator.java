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
                response = At2pcCoorManager.getInstance().register2pcParticipant(this, registerPara);
                break;

            //TODO s
            case VOLATILE2PC:
                break;
        }
        return response;
    }

    /**
     * start 2PC.
     */
    public void commit() {

    }

    public AtInitiatorCoor getInitiatorCoor() {
        return initiatorCoor;
    }

    public void setInitiatorCoor(AtInitiatorCoor initiatorCoor) {
        this.initiatorCoor = initiatorCoor;
    }

    public void addD2pdCoor(At2pcCoor d2pcCoor) {
        d2pcCoors.add(d2pcCoor);
    }

    public static enum States {
        None,
        Active,
        Preparing,
        Prepared,
        PreparedSuccess,
        Committing,
        Aborting
    }
}