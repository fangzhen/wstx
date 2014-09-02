package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class of coordinator side protocol service managers
 */
public abstract class AtAbstractCoorManager {
    private static Log __LOG = LogFactory.getLog(AtAbstractCoorManager.class);

    /** protocol service coordinator address */
    protected String coorServiceAddr;

    /** protocol service coordiantors that managed by the manager */
    protected Map<String, AtProtocolServiceCoor> managedCoordinators = new HashMap<>();

    public AtProtocolServiceCoor retrieveProtocolCoordinator(String id){
        AtProtocolServiceCoor coor = managedCoordinators.get(id);
        if (coor == null){
            if (__LOG.isWarnEnabled()){
                __LOG.warn("failed to retrieve corresponding protocol service " +
                        "coordinator with id" + id);
            }
        }
        return coor;
    }

    protected RegisterResponseType constructRegisterResponse(String privateId){
        EndpointReferenceType d2pcCoorEpr = EprUtils.createCxfEprInstance(
                coorServiceAddr, new PrivateIdType(privateId));
        RegisterResponseType response = new RegisterResponseType();
        response.setCoordinatorProtocolService(d2pcCoorEpr);
        return  response;
    }

    public void setCoorServiceAddr(String addr){
        this.coorServiceAddr = addr;
    }
    public String getCoorServiceAddr(){
        return coorServiceAddr;
    }
}
