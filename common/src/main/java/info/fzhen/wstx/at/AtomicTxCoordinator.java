package info.fzhen.wstx.at;

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
 * @author fangzhen
 *
 */
public class AtomicTxCoordinator extends AbstractActivityCoordinatorContext{
	/**
	 * EPRs of protocol participants
	 */
	private EndpointReferenceType initiatorEpr;
	private List<EndpointReferenceType> durable2PcPrtcpEprs = new ArrayList<>();
	private List<EndpointReferenceType> volatile2PcPrtcpEprs = new ArrayList<>();
	
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

    /**
     * initiate protocol service coordinator side eprs
     */
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
		AtProtocol protocol = AtProtocol.fromString(protocolId);
		switch (protocol) {
		case COMPLETION:
			setInitiatorEpr(pEpr);
			response.setCoordinatorProtocolService(coorInitiatorEpr);
			break;
		case DURABLE2PC:
            durable2PcPrtcpEprs.add(pEpr);
            response.setCoordinatorProtocolService(coor2PcEpr);
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
     *
     */
    public void commit() {

    }
	public EndpointReferenceType getCoorInitiatorEpr() {
		return coorInitiatorEpr;
	}

	public void setCoorInitiatorEpr(EndpointReferenceType coorInitiatorEpr) {
		this.coorInitiatorEpr = coorInitiatorEpr;
	}


}
