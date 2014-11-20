package info.fzhen.wstx.ba.completion;

import info.fzhen.wstx.AbstractParticipantProtocolService;
import info.fzhen.wstx.ba.WsBusinessActicity;

public class CompletionParticipantProtocolService extends AbstractParticipantProtocolService{
	private WsBusinessActicity activity;

	public void setActivity(WsBusinessActicity activity) {
		this.activity = activity;
	}

	public WsBusinessActicity getActivity() {
		return activity;
	}

	public void completed() {
		activity.completed();
	}
}
