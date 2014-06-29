package info.fzhen.wstx.transaction;

import org.apache.cxf.ws.addressing.EndpointReferenceType;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.participant.at.ATInitiator;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * WSAT transaction instance.
 * @author fangzhen
 *
 */
public class WsatTransaction extends WsTransaction {
	private CoordinationContext coordinationContext;
	
	/** Protocol coordinator service */
	private EndpointReferenceType coorInitiatorEpr;
	
	/**participants TODO: are they necessary?*/
	private ATInitiator initiator;
	
	public WsatTransaction(){
	}
	@Override
	public void begin() {
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ActivationPortType activationSer = config.getActivationSer();
		ccc.setCoordinationType(config.getCoordinationType());
		CreateCoordinationContextResponseType res = activationSer.createCoordinationContextOperation(ccc);
		setCoordinationContext(res.getCoordinationContext());
	}
	public CoordinationContext getCoordinationContext() {
		return coordinationContext;
	}
	public void setCoordinationContext(CoordinationContext coordinationContext) {
		this.coordinationContext = coordinationContext;
	}

	public void setInitiator(ATInitiator initiator) {
		if (this.initiator != null){
			throw new WstxRtException("Too many initiators. One is enough");
		}
		this.initiator = initiator;		
	}
	public void setCoorInitiatorEpr(EndpointReferenceType coorInitiatorEpr) {
		this.coorInitiatorEpr = coorInitiatorEpr;
	}
	public void commit() {
		
	}
}
