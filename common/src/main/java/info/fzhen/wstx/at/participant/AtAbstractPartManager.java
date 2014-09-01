package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.coordinator.PrivateIdType;
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
 * Base class of participant side protocol service managers
 */
public class AtAbstractPartManager {
    /** participant side protocol service address */
    protected String partServiceAddr;
    /** Map that holds all participants managed by this manager, id as key*/
    protected Map<String, AtProtocolServicePart> managedParticipants = new HashMap<>();

    public String getPartServiceAddr() {
        return partServiceAddr;
    }

    public void setPartServiceAddr(String partServiceAddr) {
        this.partServiceAddr = partServiceAddr;
    }

    public void doRegister(CoordinationContext coorContext, AtProtocolServicePart participant, String protocolId){
        String id = CommonUtils.genPrivateId();
        managedParticipants.put(id, participant);

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
        participant.setCoordinatorEpr(coorEpr);
    }
}
