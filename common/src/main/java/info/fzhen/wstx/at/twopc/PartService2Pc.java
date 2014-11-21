package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.Participant;
import info.fzhen.wstx.StateEnum;

public abstract class PartService2Pc <T extends Participant, E, R extends StateEnum>
		extends AbstractParticipantProtocolService<T, E, R> {
	public abstract void prepare();
	public abstract void commit();

	public static enum Vote {
		Prepared,
		Aborted,
		ReadOnly
	}
}
