package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.Participant;

public interface At2pcParticipant extends Participant {
	public At2pcPartService.Vote prepare();

	public void commit();

	public void rollback();

	public void unknown();
}
