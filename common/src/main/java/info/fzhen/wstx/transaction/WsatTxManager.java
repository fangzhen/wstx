package info.fzhen.wstx.transaction;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.AtProtocol;
import info.fzhen.wstx.config.ATPartEprConfig;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.participant.at.ATInitiator;
import info.fzhen.wstx.participant.at.Durable2PCParticipant;
import info.fzhen.wstx.participant.at.Volatile2PCParticipant;
import info.fzhen.wstx.util.EprUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

/**
 * Global transaction manager of the site. It also holds global context.
 * Used by initiator of Completion protocol participant side.
 * 
 * @author fangzhen
 * 
 */
public class WsatTxManager {
	private static final Log __log = LogFactory.getLog(Process.class);
	
	private static WsatTxManager instance;
	private ATPartEprConfig eprConfiguration;
	
	/**Participants magagered by this manager (on this site) */
	Map<String, ATInitiator> initiators = new HashMap<String, ATInitiator>();
	Map<String, Durable2PCParticipant> durable2PcParticipants = new HashMap<>();
	Map<String, Volatile2PCParticipant> volatile2PcParticipants = new HashMap<>();

	public static WsatTxManager getInstance() {
		if (instance == null){
			__log.error("Atomatic Transaction Manager(WsatTxManager) hasn't been initiated");
			throw new WstxRtException("Atomatic Transaction Manager(WsatTxManager) hasn't been initiated");
		}
		return instance;
	}

	public static void setInstance(WsatTxManager instance) {
		WsatTxManager.instance = instance;
	}

	public void registerInitiator(WsatTransaction transaction) {
		ATInitiator initiator = new ATInitiator();
		initiator.setTransaction(transaction);
		transaction.setInitiator(initiator);

		String id = genPrivateId();
		initiators.put(id, initiator);

		//completion protocol participant
		PrivateIdType pit = new PrivateIdType(id);
		String addr = eprConfiguration.getInitiatorAddress();
		EndpointReferenceType initiatorEprCXF = EprUtils.createCxfEprInstance(addr, pit);
		
		RegisterType reg = new RegisterType();
		reg.setParticipantProtocolService(initiatorEprCXF);
		reg.setProtocolIdentifier(AtProtocol.COMPLETION.getText());
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
	
	private String genPrivateId() {
		// TODO should ensure it to be unique. simply random
		return ""+new Random().nextInt();
	}
	
	public ATInitiator getInitiator(String id){
		return initiators.get(id);
	}

	public void forgetInitiator(String txId) {
		initiators.remove(txId);
	}
}
