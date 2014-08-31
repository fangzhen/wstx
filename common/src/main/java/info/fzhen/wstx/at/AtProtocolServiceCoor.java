package info.fzhen.wstx.at;

import org.apache.cxf.ws.addressing.EndpointReferenceType;

public abstract class AtProtocolServiceCoor {
    /**The activity it belongs to*/
    protected AtomicTxCoordinator activity;
    protected EndpointReferenceType participantEpr;

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
}
