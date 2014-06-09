package info.fzhen.wstx.transaction;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.wsat.CompletionInitiatorPort;

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
	
	//participants
	private CompletionInitiatorPort initiator;
	
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

	public void setInitiator(CompletionInitiatorPort initiator) {
		if (this.initiator != null){
			throw new WstxRtException("Too many initiators. One is enough");
		}
		this.initiator = initiator;		
	}
}
