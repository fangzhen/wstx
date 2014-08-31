package info.fzhen.wstx.participant.at;

import info.fzhen.wstx.transaction.WsatTransaction;

public class Volatile2PCParticipant extends Participant2PC{
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
