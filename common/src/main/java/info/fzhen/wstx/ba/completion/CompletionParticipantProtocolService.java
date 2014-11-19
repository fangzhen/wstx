package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.ba.WsBusinessActicity;

public class CompletionParticipantProtocolService extends AbstractParticipantProtocolService{
	private WsBusinessActicity acticity;

	public void setActicity(WsBusinessActicity acticity) {
		this.acticity = acticity;
	}

	public WsBusinessActicity getActicity() {
		return acticity;
	}
}
