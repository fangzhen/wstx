package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.StateEnum;
import org.oasis_open.docs.ws_tx.wsat._2006._06.CompletionInitiatorPortType;

public class AtInitiatorCoor extends AtProtocolServiceCoor<CompletionInitiatorPortType>{

    public static enum  State implements StateEnum {
        None,
        Active,
        Completing
    }
}
