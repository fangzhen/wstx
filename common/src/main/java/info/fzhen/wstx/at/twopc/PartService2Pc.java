package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.Participant;
import info.fzhen.wstx.at.AtProtocolServicePart;

public abstract class PartService2Pc <T extends Participant, E>
		extends AtProtocolServicePart<T, E>{
	public abstract void prepare();
	public abstract void commit();

	public static enum Vote {
		Prepared,
		Aborted,
		ReadOnly
	}
}
