package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

/**
 * Abstract class of protocol coordinator service
 * @param <T> type of corresponding participant service port type
 */
public abstract class AtProtocolServiceCoor <T>{
    /**The activity it belongs to*/
    protected AtomicTxCoordinator activity;

    /**participant protocol service EPR*/
    protected EndpointReferenceType participantEpr;
    private T PartProxy;
    /** state of the coordiantor */
    protected StateEnum state;

    protected T getParticipantProxy(Class<T> clazz){
//        if (PartProxy == null){
        if (true){ //TODO maybe a bug of CXF
            PartProxy = EprUtils.createWsaddrClientProxy(clazz, participantEpr);
        }
        return PartProxy;
    }

    public AtomicTxCoordinator getActivity() {
        return activity;
    }

    public void setActivity(AtomicTxCoordinator activity) {
        this.activity = activity;
    }

    public EndpointReferenceType getParticipantEpr() {
        return participantEpr;
    }

    public void setParticipantEpr(EndpointReferenceType participantEpr) {
        this.participantEpr = participantEpr;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}
