package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.participant.Participant;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

/**
 * Abstract participant side protocol service.
 * @param <T> corresponding functional participant
 * @param <E> corresponding coordinator protocol service port type
 */
public abstract class AtProtocolServicePart <T extends Participant, E>{
    /**coordinator protocol service EPR*/
    protected EndpointReferenceType coordinatorEpr;
    private E coordinatorProxy;

    /**corresponding functional participant */
    protected T participant;

    protected StateEnum state;

    public EndpointReferenceType getCoordinatorEpr() {
        return coordinatorEpr;
    }
    public E getCoordinatorProxy(Class<E> clazz){
        coordinatorProxy = EprUtils.createWsaddrClientProxy(clazz, coordinatorEpr);
        return coordinatorProxy;
    }

    public void setCoordinatorEpr(EndpointReferenceType coordinatorEpr) {
        this.coordinatorEpr = coordinatorEpr;
    }

    public T getParticipant() {
        return participant;
    }

    public void setParticipant(T participant) {
        this.participant = participant;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}
