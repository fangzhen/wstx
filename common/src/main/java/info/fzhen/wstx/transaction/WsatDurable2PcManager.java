package info.fzhen.wstx.transaction;

import info.fzhen.wstx.at.coordinator.AtProtocol;
import info.fzhen.wstx.config.ATPartEprConfig;
import info.fzhen.wstx.at.participant.Durable2PCParticipant;
import info.fzhen.wstx.util.CommonUtils;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

import java.util.HashMap;
import java.util.Map;

/**
 * Participant side durable2Pc participant manager. This manager is
 * singleton to the site which manages all the participants.
 */
public class WsatDurable2PcManager {
    /** singleton */
    private static WsatDurable2PcManager instance;

    /** Map that holds all durable 2PC participants, id as key*/
	private Map<String, Durable2PCParticipant> durable2PcParticipants = new HashMap<>();
    private ATPartEprConfig atPartEprConfig;

    public static WsatDurable2PcManager getInstance(){
        synchronized (WsatDurable2PcManager.class){
            if (instance == null){
                instance = new WsatDurable2PcManager();
            }
        }
        return instance;
    }

    /**
     * register durable 2PC protocol
     * @param coorContext transaction coordination context
     * @param participant the participant to be registered.
     */
    public void registerDurable2Pc(CoordinationContext coorContext, Durable2PCParticipant participant){
        String id = CommonUtils.genPrivateId();
        durable2PcParticipants.put(id, participant);

        //registration information
        String d2pcPartAddr = atPartEprConfig.getDuarble2PcPtcpAddress();
        EndpointReferenceType d2pcPartEpr = EprUtils.createCxfEprInstance(d2pcPartAddr, id);
        RegisterType registerInfo = new RegisterType();
        registerInfo.setParticipantProtocolService(d2pcPartEpr);
        registerInfo.setProtocolIdentifier(AtProtocol.DURABLE2PC.getText());

        // registration service proxy
        EndpointReferenceType regSerEpr = coorContext.getRegistrationService();
        RegistrationPortType regSerClient = EprUtils.createWsaddrClientProxy(
                RegistrationPortType.class, regSerEpr);
        RegisterResponseType response = regSerClient.registerOperation(registerInfo);

        EndpointReferenceType coorD2pcEpr = response.getCoordinatorProtocolService();
        participant.setCoordinatorEpr(coorD2pcEpr);
    }

    public void setAtPartEprConfig(ATPartEprConfig atPartEprConfig) {
        this.atPartEprConfig = atPartEprConfig;
    }
}
