package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.util.EprUtils;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * WSAT transaction instance. It is used in initiator side of
 * CompletionProtocol. It provides a useful interface to applications.
 * 
 * @author fangzhen
 */
public class WsatTransaction extends WsTransaction {
	/**completion participant */
	private AtInitiatorPart initiator;

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
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ActivationPortType activationSer = getActivationSer();
		ccc.setCoordinationType(CoordinationType.WSAT.getText());
		CreateCoordinationContextResponseType res = activationSer
				.createCoordinationContextOperation(ccc);
		setCoordinationContext(res.getCoordinationContext());
	}

	public void setInitiator(AtInitiatorPart initiator) {
		if (this.initiator != null) {
			throw new WstxRtException("Too many initiators. One is enough");
		}
		this.initiator = initiator;
	}

	public void commit() {
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
