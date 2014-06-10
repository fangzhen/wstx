package info.fzhen.wstx.participant.at;

import info.fzhen.wstx.participant.Participant;

public interface Participant2PC extends Participant{
	public Vote prepare ();
    public void commit ();
    public void rollback ();
    public void unknown ();
    public static interface Vote{
    	
    }
}
