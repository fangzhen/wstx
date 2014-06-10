package info.fzhen.wstx.participant;

public class ParticipantManager {
	private static ParticipantManager instance;
	private ParticipantContext context;
	
	public static ParticipantManager getInstance() {
		return instance;
	}

	public static void setInstance(ParticipantManager instance) {
		ParticipantManager.instance = instance;
	}

	public ParticipantContext getContext() {
		return context;
	}

	public void setContext(ParticipantContext context) {
		this.context = context;
	}
	
}
