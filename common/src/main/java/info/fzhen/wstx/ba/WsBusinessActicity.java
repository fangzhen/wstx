package info.fzhen.wstx.ba;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WsTransaction;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolMgr;
import info.fzhen.wstx.ba.completion.CompletionParticipantProtocolService;
import info.fzhen.wstx.coordinator.PrivateIdType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * business activity instance for application to begin, commit...
 * an activity
 */
public class WsBusinessActicity extends WsTransaction {
	private static final Log __LOG = LogFactory.getLog(WsBusinessActicity.class);

	/** coordination type of the activity. {@link CoordinationType.WSBA_ATOMIC}
	 * or {@link  CoordinationType.WSBA_MIXED} */
	private CoordinationType type;
	private CompletionParticipantProtocolService initiator;

	@Override
	public void begin() {
		if (__LOG.isInfoEnabled()){
			__LOG.info("Starting a business activity");
		}
		CreateCoordinationContextType ccc = new CreateCoordinationContextType();
		ccc.setCoordinationType(type.getText());
		ActivationPortType activationSer = getActivationSer();
		CreateCoordinationContextResponseType response =
				activationSer.createCoordinationContextOperation(ccc);

		setCoordinationContext(response.getCoordinationContext());
		if (__LOG.isDebugEnabled()){
			CoordinationContext ctx = response.getCoordinationContext();
			EndpointReferenceType regEpr = ctx.getRegistrationService();
			String id = null;
			try {
				id = ((PrivateIdType) regEpr.getReferenceParameters().getAny().get(0)).getPrivateId();
			} catch (Exception e) {
				id = "Unable to retrieve private id";
			}
			__LOG.debug("started Business activity. " +
					"Registration Service address: " + regEpr.getAddress().getValue() +
					" activity id: " + id);
		}
		CompletionParticipantProtocolMgr mgr = CompletionParticipantProtocolMgr.getInstance();
		mgr.reigsterInitiator(this);
	}

	public CoordinationType getType() {
		return type;
	}

	public void setType(CoordinationType type) {
		if (type != CoordinationType.WSBA_ATOMIC &&
				type != CoordinationType.WSBA_MIXED){
			throw new WstxRtException("Coordiantion type of business activity " +
					"must be " + CoordinationType.WSBA_ATOMIC.getText() + " or " +
					CoordinationType.WSBA_MIXED.getText());
		}
		this.type = type;
	}

	public void setInitiator(CompletionParticipantProtocolService initiator) {
		this.initiator = initiator;
	}
}
