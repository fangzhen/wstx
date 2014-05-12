package info.fzhen.wstx.at;

public interface Participant2PC {
	public Vote prepare ();
    public void commit ();
    public void rollback ();
    public void unknown ();
    public static interface Vote{
    	
    }
}
