package info.fzhen.wstx.at;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.config.AtCoorEprConfig;
import info.fzhen.wstx.context.AbstractActivityCoordinatorContext;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;

import java.util.List;

import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
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
	private EndpointReferenceType initiatorEpr;
	private List<EndpointReferenceType> durable2PcPrtcpEprs;
	private List<EndpointReferenceType> volatile2PcPrtcpEprs;
	
	/**
	 * EPRs of protocol coordinators
	 */
	private EndpointReferenceType coorInitiatorEpr;
	private EndpointReferenceType coor2PcEpr;
	
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
		coorInitiatorEpr = EprUtils.createCxfEprInstance(addr, pit);
		addr =  coordinatorManager.<AtCoorEprConfig>getTypeCoorEprConfig(coordinationType).getAt2PcCoorAddress();
		coor2PcEpr = EprUtils.createCxfEprInstance(addr, pit);
	}

	@Override
	public RegisterResponseType register(RegisterType registerPara) {
		String protocolId = registerPara.getProtocolIdentifier();
		EndpointReferenceType pEpr = registerPara.getParticipantProtocolService();
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

	public EndpointReferenceType getInitiatorEpr() {
		return initiatorEpr;
	}

	public void setInitiatorEpr(EndpointReferenceType initiatorEpr) {
		this.initiatorEpr = initiatorEpr;
	}

	public EndpointReferenceType getCoorInitiatorEpr() {
		return coorInitiatorEpr;
	}

	public void setCoorInitiatorEpr(EndpointReferenceType coorInitiatorEpr) {
		this.coorInitiatorEpr = coorInitiatorEpr;
	}

}
