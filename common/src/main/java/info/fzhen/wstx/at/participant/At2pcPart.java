package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.participant.IState;

public abstract class At2pcPart extends AtProtocolServicePart{
    public abstract Vote prepare ();
    public abstract void commit ();
    public abstract void rollback ();
    public abstract void unknown ();

    public static enum Vote{
    	Prepared,
        Aborted,
        ReadOnly
    }

    public static enum State implements IState{
        None,
        Active,
        Preparing,
        Prepared,
        PrepareSuccess,
        Committing
    }
}
