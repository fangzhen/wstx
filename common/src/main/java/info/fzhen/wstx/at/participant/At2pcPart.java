package info.fzhen.wstx.at.participant;

public abstract class At2pcPart extends  AtProtocolServicePart{
    public abstract Vote prepare ();
    public abstract void commit ();
    public abstract void rollback ();
    public abstract void unknown ();

    public enum Vote{
    	Prepared,
        Aborted;
    }
}
