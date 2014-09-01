package info.fzhen.wstx.at.participant;

import org.apache.cxf.ws.addressing.EndpointReferenceType;

public abstract class AtProtocolServicePart {
    /**coordinator protocol service EPR*/
    protected EndpointReferenceType coordinatorEpr;

    public EndpointReferenceType getCoordinatorEpr() {
        return coordinatorEpr;
    }

    public void setCoordinatorEpr(EndpointReferenceType coordinatorEpr) {
        this.coordinatorEpr = coordinatorEpr;
    }
}
