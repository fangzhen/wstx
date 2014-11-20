package info.fzhen.wstx.ba.cc;

import info.fzhen.wstx.Participant;

/**
 * Coordinator completion participant
 */
public interface CcParticipant extends Participant{
	Vote complete();
	void close();
	void cancel();
	void compensate();

	public static enum Vote{
		Completed,
		CannotComplete,
		Fail
	}
}
