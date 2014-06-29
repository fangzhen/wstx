package info.fzhen.wstx.transaction;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.config.ATPartEprConfig;
import info.fzhen.wstx.participant.at.ATInitiator;
import info.fzhen.wstx.util.EprUtils;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

/**
 * Global transaction manager of the site. It also holds global context.
 * 
 * @author fangzhen
 * 
 */
public class WsatTxManager {
	private static WsatTxManager instance;
	private ATPartEprConfig eprConfiguration;
	
	public static WsatTxManager getInstance() {
		return instance;
	}

	public static void setInstance(WsatTxManager instance) {
		WsatTxManager.instance = instance;
	}

	public void registerInitiator(ATInitiator initiator,
			WsatTransaction transaction) {
		transaction.setInitiator(initiator);

		EndpointReferenceType initiatorEprCXF = new EndpointReferenceType();
		AttributedURIType addr = new AttributedURIType();
		addr.setValue(eprConfiguration.getInitiatorAddress());
		initiatorEprCXF.setAddress(addr);
		RegisterType reg = new RegisterType();
		reg.setParticipantProtocolService(initiatorEprCXF);
		reg.setProtocolIdentifier(Constants.WSATType.COMPLETION_PROTOCOL);
		EndpointReferenceType regSerCXF = transaction.getCoordinationContext()
				.getRegistrationService();
		RegistrationPortType client = EprUtils.createWsaddrClientProxy(RegistrationPortType.class, regSerCXF);
		RegisterResponseType response = client.registerOperation(reg);
		
		EndpointReferenceType coorInitiatorEpr = response.getCoordinatorProtocolService();
		transaction.setCoorInitiatorEpr(coorInitiatorEpr);
	}

	public void setEprConfiguration(ATPartEprConfig eprConfig) {
		this.eprConfiguration = eprConfig;
	}
}
