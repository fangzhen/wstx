package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.StateEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ParticipantPortType;

public class At2pcCoor extends AtProtocolServiceCoor {
    private static Log __LOG = LogFactory.getLog(At2pcCoor.class);

    public void prepare() {
        if (__LOG.isInfoEnabled()){
            __LOG.info("Parpare parcipant " + participantEpr.getAddress().getValue());
        }
        if (state == State.Active){
            ParticipantPortType partProxy = getPartProxy(ParticipantPortType.class);
            partProxy.prepareOperation(new Notification());
            state = State.Preparing;
        }else{
            //TODO handle other states
        }
    }

    public static enum State implements StateEnum {
        None,
        Active,
        Preparing,
        Prepared,
        PrepareSuccess,
        Committing,
        Aborting
    }
}
