package info.fzhen.wstx.at;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.config.AtCoorEprConfig;
import info.fzhen.wstx.context.AbstractActivityCoordinatorContext;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.W3CEndpointReferenceUtils;

import java.util.List;

import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
/**
 * Atomic transaction coordinator context instance. 
 * @author fangzhen
 *
 */
public class AtomicTxCoordinator extends AbstractActivityCoordinatorContext{
	/**
	 * EPRs of protocol participants
	 */
	private W3CEndpointReference initiatorEpr;
	private List<W3CEndpointReference> durable2PcPrtcpEprs;
	private List<W3CEndpointReference> volatile2PcPrtcpEprs;
	
	/**
	 * EPRs of protocol coordinators
	 */
	private W3CEndpointReference coorInitiatorEpr;
	private W3CEndpointReference coor2PcEpr;
	
	/**
	 * Factory method that create new instance of Atomic Tx.
	 * @param ccc
	 * @param pirvateId
	 * @return
	 */
	public static AtomicTxCoordinator createInstance(CreateCoordinationContextType ccc, String pirvateId){
		AtomicTxCoordinator activityCoordinatorContext = new AtomicTxCoordinator();
		activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
		if (ccc.getExpires() != null){
			activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
		}
		activityCoordinatorContext.setPrivateId(pirvateId);
		activityCoordinatorContext.initiateCoorEprs();
		return activityCoordinatorContext;
	}
	
	private void initiateCoorEprs() {
		PrivateIdType pit = new PrivateIdType(privateId);
		String addr = coordinatorManager.<AtCoorEprConfig>getTypeCoorEprConfig(coordinationType).getAtCompletionCoorAddress();
		coorInitiatorEpr = W3CEndpointReferenceUtils.createInstance(addr, pit);
		addr =  coordinatorManager.<AtCoorEprConfig>getTypeCoorEprConfig(coordinationType).getAt2PcCoorAddress();
		coor2PcEpr = W3CEndpointReferenceUtils.createInstance(addr, pit);
	}

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		W3CEndpointReference pEpr = registerPara.getParticipantProtocolService();
		RegisterResponseType response = new RegisterResponseType();
		switch (protocolId) {
		case Constants.WSATType.COMPLETION_PROTOCOL:
			setInitiatorEpr(pEpr);
			response.setCoordinatorProtocolService(coorInitiatorEpr);
			break;
	//TODO s	
		case Constants.WSATType.DURABLE2PC_PROTOCOL:
			break;

		case Constants.WSATType.VOLATILE2PC_PROTOCOL:
			break;
			
		default:
			//TODO: illegal protocol
			break;
		}
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
