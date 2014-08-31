package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.participant.Participant;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public abstract class Participant2PC implements Participant{
    private EndpointReferenceType coordinatorEpr;

    public void setCoordinatorEpr(EndpointReferenceType coordinatorEpr) {
        this.coordinatorEpr = coordinatorEpr;
    }

    public abstract Vote prepare ();
    public abstract void commit ();
    public abstract void rollback ();
    public abstract void unknown ();

    public enum Vote{
    	Prepared,
        Aborted;
    }
}
