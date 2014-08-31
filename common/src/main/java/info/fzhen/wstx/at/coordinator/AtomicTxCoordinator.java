package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.config.AtCoorEprConfig;
import info.fzhen.wstx.context.AbstractActivityCoordinatorContext;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
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
    /**
     * TODO these fileds should be removed.
     * EPRs of protocol participants
     */
    private EndpointReferenceType initiatorEpr;
    private List<EndpointReferenceType> durable2PcPrtcpEprs = new ArrayList<>();
    private List<EndpointReferenceType> volatile2PcPrtcpEprs = new ArrayList<>();

    /**
     * TODO these fields should be removed
     * EPRs of protocol coordinators
     */
    private EndpointReferenceType coorInitiatorEpr;
    private EndpointReferenceType coor2PcEpr;

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
        activityCoordinatorContext.initiateCoorEprs();
        return activityCoordinatorContext;
    }

    /**
     * initiate protocol service coordinator side eprs
     */
    private void initiateCoorEprs() {
        PrivateIdType pit = new PrivateIdType(privateId);
        String addr = coordinatorManager.<AtCoorEprConfig>getTypeCoorEprConfig(coordinationType).getAtCompletionCoorAddress();
        coorInitiatorEpr = EprUtils.createCxfEprInstance(addr, pit);
        addr = coordinatorManager.<AtCoorEprConfig>getTypeCoorEprConfig(coordinationType).getAt2PcCoorAddress();
        coor2PcEpr = EprUtils.createCxfEprInstance(addr, pit);
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

    public EndpointReferenceType getInitiatorEpr() {
        return initiatorEpr;
    }

    public void setInitiatorEpr(EndpointReferenceType initiatorEpr) {
        this.initiatorEpr = initiatorEpr;
    }

    /**
     * start 2PC.
     */
    public void commit() {
        //TODO First Volatile
    }

    public EndpointReferenceType getCoorInitiatorEpr() {
        return coorInitiatorEpr;
    }

    public void setCoorInitiatorEpr(EndpointReferenceType coorInitiatorEpr) {
        this.coorInitiatorEpr = coorInitiatorEpr;
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