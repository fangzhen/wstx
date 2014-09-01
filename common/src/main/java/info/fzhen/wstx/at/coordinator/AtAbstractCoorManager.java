package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;

/**
 * Base class of coordinator side protocol service managers
 */
public abstract class AtAbstractCoorManager {
    protected String coorServiceAddr;

    public void setCoorServiceAddr(String addr){
        this.coorServiceAddr = addr;
    }
    public String getCoorServiceAddr(){
        return coorServiceAddr;
    }
    protected RegisterResponseType constructRegisterResponse(String privateId){
        EndpointReferenceType d2pcCoorEpr = EprUtils.createCxfEprInstance(
                coorServiceAddr, new PrivateIdType(privateId));
        RegisterResponseType response = new RegisterResponseType();
        response.setCoordinatorProtocolService(d2pcCoorEpr);
        return  response;
    }
}
