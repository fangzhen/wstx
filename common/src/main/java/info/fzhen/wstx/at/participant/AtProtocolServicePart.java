package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.participant.Participant;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

/**
 * Abstract participant side protocol service.
 */
public abstract class AtProtocolServicePart <T extends Participant>{
    /**coordinator protocol service EPR*/
    protected EndpointReferenceType coordinatorEpr;

    /**corresponding functional participant */
    protected T participant;

    public EndpointReferenceType getCoordinatorEpr() {
        return coordinatorEpr;
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
}
