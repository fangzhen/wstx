package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.Participant;

public abstract class PartService2Pc <T extends Participant, E>
		extends AbstractParticipantProtocolService<T, E> {
	public abstract void prepare();
	public abstract void commit();

	public static enum Vote {
		Prepared,
		Aborted,
		ReadOnly
	}
}
