package info.fzhen.wstx.at.coordinator;


import info.fzhen.wstx.participant.IState;

public class AtInitiatorCoor extends AtProtocolServiceCoor {

    public static enum  State implements IState {
        None,
        Active,
        Completing
    }
}
