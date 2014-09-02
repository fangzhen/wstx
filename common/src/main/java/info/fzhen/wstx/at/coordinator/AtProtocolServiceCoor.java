package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.participant.IState;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public abstract class AtProtocolServiceCoor {
    /**The activity it belongs to*/
    protected AtomicTxCoordinator activity;

    /**participant protocol service EPR*/
    protected EndpointReferenceType participantEpr;
    /** state of the coordiantor */
    protected IState state;

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

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}
