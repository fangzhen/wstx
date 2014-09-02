package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.participant.IState;

public class At2pcCoor extends AtProtocolServiceCoor {

    public static enum State implements IState{
        None,
        Active,
        Preparing,
        Prepared,
        PrepareSuccess,
        Committing,
        Aborting
    }
}
