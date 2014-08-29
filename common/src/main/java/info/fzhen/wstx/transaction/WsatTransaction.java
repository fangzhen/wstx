package info.fzhen.wstx.transaction;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.participant.at.ATInitiator;
import info.fzhen.wstx.util.EprUtils;

import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionCoordinatorPortType;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * WSAT transaction instance. It is used in initiator side of
 * CompletionProtocol.
 * 
 * @author fangzhen
 */
public class WsatTransaction extends WsTransaction {
	/** Completion protocol coordinator service */
	private EndpointReferenceType coorInitiatorEpr;

	/**completion participant */
	private ATInitiator initiator;

	public WsatTransaction() {
	}

	/**
	 * Begin a atomic transaction, that is, call activation service of
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

	public void setInitiator(ATInitiator initiator) {
		if (this.initiator != null) {
			throw new WstxRtException("Too many initiators. One is enough");
		}
		this.initiator = initiator;
	}

	public void setCoorInitiatorEpr(EndpointReferenceType coorInitiatorEpr) {
		this.coorInitiatorEpr = coorInitiatorEpr;
	}

	public void commit() {
		CompletionCoordinatorPortType completionCoordinator = EprUtils
				.createWsaddrClientProxy(CompletionCoordinatorPortType.class,
						coorInitiatorEpr);
		completionCoordinator.commitOperation(new Notification());
	}
	
	public void rollback() {
		//TODO
	}
}
