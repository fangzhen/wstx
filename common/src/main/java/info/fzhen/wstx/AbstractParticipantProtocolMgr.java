package info.fzhen.wstx;

import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.CommonUtils;
import info.fzhen.wstx.util.EprUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class of participant side protocol service managers
 */
public class AbstractParticipantProtocolMgr<T extends AbstractParticipantProtocolService> {
	private static Log __LOG = LogFactory.getLog(AbstractParticipantProtocolMgr.class);

	/**
	 * participant side protocol service address
	 */
	protected String partServiceAddr;
	/**
	 * Map that holds all participants managed by this manager, id as key
	 */
	protected Map<String, T> managedParticipants = new HashMap<>();

	public T retrieveProtocolParticipant(String id) {
		T part = managedParticipants.get(id);
		if (part == null) {
			if (__LOG.isWarnEnabled()) {
				__LOG.warn("failed to retrieve corresponding protocol service participant " +
						"coordinator with id" + id);
			}
		}
		return part;
	}

	/**
	 * Register participant
	 *
	 * @param coorContext    coordination context of the transaction that the participant take part in
	 * @param participantSer the target participant
	 * @param protocolId     protocol identifier
	 */
	protected void doRegister(CoordinationContext coorContext, T participantSer, String protocolId) {
		String id = CommonUtils.genPrivateId();
		managedParticipants.put(id, participantSer);

		//registration information
		EndpointReferenceType PartEpr = EprUtils.createCxfEprInstance(partServiceAddr, new PrivateIdType(id));
		RegisterType registerInfo = new RegisterType();
		registerInfo.setParticipantProtocolService(PartEpr);
		registerInfo.setProtocolIdentifier(protocolId);

		// registration service proxy
		EndpointReferenceType regSerEpr = coorContext.getRegistrationService();
		RegistrationPortType regSerClient = EprUtils.createWsaddrClientProxy(
				RegistrationPortType.class, regSerEpr);
		RegisterResponseType response = regSerClient.registerOperation(registerInfo);

		EndpointReferenceType coorEpr = response.getCoordinatorProtocolService();
		participantSer.setCoordinatorEpr(coorEpr);
	}

	public String getPartServiceAddr() {
		return partServiceAddr;
	}

	public void setPartServiceAddr(String partServiceAddr) {
		this.partServiceAddr = partServiceAddr;
	}
}
