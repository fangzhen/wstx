package info.fzhen.wstx.participant.at;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.transaction.WsTransaction;
import info.fzhen.wstx.transaction.WsatTransaction;

public class Volatile2PCParticipant implements Participant2PC{
	private WsatTransaction transaction;
	@Override
	public Vote prepare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unknown() {
		// TODO Auto-generated method stub
		
	}
}
