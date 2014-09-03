package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * WSAT transaction instance. It is used in initiator side of
 * CompletionProtocol. It provides a useful interface to applications.
 * 
 * @author fangzhen
 */
public class WsatTransaction extends WsTransaction {
    private static final Log __LOG = LogFactory.getLog(WsatTransaction.class);
	/**completion participant */
	private AtInitiatorPartService initiator;

    /**client proxy of coordinator activation service */
    private ActivationPortType activationSer;

	public WsatTransaction() {
	}

	/**
	 * Begin an atomic transaction, that is, call activation service of
	 * coordinator and receive the returned {@link CreateCoordinationContextResponseType}
	 */
	@Override
	public void begin() {
        if (__LOG.isInfoEnabled()){
            __LOG.info("start an atomic transaction now.");
        }
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ActivationPortType activationSer = getActivationSer();
		ccc.setCoordinationType(CoordinationType.WSAT.getText());
		CreateCoordinationContextResponseType res = activationSer
				.createCoordinationContextOperation(ccc);
		setCoordinationContext(res.getCoordinationContext());
        if (__LOG.isDebugEnabled()){
            CoordinationContext ctx = res.getCoordinationContext();
            EndpointReferenceType regEpr = ctx.getRegistrationService();
            String id = null;
            try{
                id = ((PrivateIdType)regEpr.getReferenceParameters().getAny().get(0)).getPrivateId();
            }catch (Exception e){
                id = "Unable to retrieve private id";
            }
            __LOG.debug("started atomic transaction. " +
                    "Registration Service address: " + regEpr.getAddress().getValue() +
                            " activity id: " + id);
        }
	}

	public void setInitiator(AtInitiatorPartService initiator) {
		if (this.initiator != null) {
			throw new WstxRtException("Too many initiators. One is enough");
		}
		this.initiator = initiator;
	}

    /**
     * commit the atomic transaction
     */
	public void commit() {
        if (__LOG.isInfoEnabled()){
            __LOG.info("start to commit the transaction");
        }
		CompletionCoordinatorPortType completionCoordinator = EprUtils
				.createWsaddrClientProxy(CompletionCoordinatorPortType.class,
						initiator.getCoordinatorEpr());
		completionCoordinator.commitOperation(new Notification());
    }
	
	public void rollback() {
		//TODO
	}
    public ActivationPortType getActivationSer() {
        return activationSer;
    }

    public void setActivationSer(ActivationPortType activationSer) {
        this.activationSer = activationSer;
    }
}
