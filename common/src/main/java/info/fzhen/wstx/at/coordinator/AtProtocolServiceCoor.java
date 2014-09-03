package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public abstract class AtProtocolServiceCoor {
    /**The activity it belongs to*/
    protected AtomicTxCoordinator activity;

    /**participant protocol service EPR*/
    protected EndpointReferenceType participantEpr;
    /** state of the coordiantor */
    protected StateEnum state;

    protected <T> T getPartProxy(Class<T> clazz){
        return EprUtils.createWsaddrClientProxy(clazz, participantEpr);
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
