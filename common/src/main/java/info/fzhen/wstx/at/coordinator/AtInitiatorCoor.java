package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.StateEnum;

public class AtInitiatorCoor extends AtProtocolServiceCoor {

    public static enum  State implements StateEnum {
        None,
        Active,
        Completing
    }
}
