package info.fzhen.wstx.at;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.context.AbstractActivityCoordinatorContext;

import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
/**
 * Atomic transaction coordinator instance. 
 * @author fangzhen
 *
 */
public class AtomicTxCoordinator extends AbstractActivityCoordinatorContext{
	private W3CEndpointReference initiatorEpr;
	private W3CEndpointReference coorInitiatorEpr;
	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		W3CEndpointReference pEpr = registerPara.getParticipantProtocolService();
		switch (protocolId) {
		case Constants.WSATType.COMPLETION_PROTOCOL:
			setInitiatorEpr(pEpr);
			break;
	//TODO s	
		case Constants.WSATType.DURABLE2PC_PROTOCOL:
			break;

		case Constants.WSATType.VOLATILE2PC_PROTOCOL:
			break;
			
		default:
			//illegal protocol
			break;
		}
		RegisterResponseType response = new RegisterResponseType();
		response.setCoordinatorProtocolService(coorInitiatorEpr);
		return response;
	}

	public W3CEndpointReference getInitiatorEpr() {
		return initiatorEpr;
	}

	public void setInitiatorEpr(W3CEndpointReference initiatorEpr) {
		this.initiatorEpr = initiatorEpr;
	}

	public W3CEndpointReference getCoorInitiatorEpr() {
		return coorInitiatorEpr;
	}

	public void setCoorInitiatorEpr(W3CEndpointReference coorInitiatorEpr) {
		this.coorInitiatorEpr = coorInitiatorEpr;
	}

}
