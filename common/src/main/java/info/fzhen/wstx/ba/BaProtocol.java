package info.fzhen.wstx.ba;

/**
 * Business Activity Protocol enumerations
 */
public enum BaProtocol {
	PARTICIPANT_COMPLETION
			("http://docs.oasis-open.org/ws-tx/wsba/2006/06/ParticipantCompletion"),
	COORDINATOR_COMPLETION(
			"http://docs.oasis-open.org/ws-tx/wsba/2006/06/CoordinatorCompletion"),
	COMPLETION("http://www.fzhen.info/ws-tx/wsba/Completion");

	private String text;

	private BaProtocol(String text) {
		this.text = text;
	}

	public static BaProtocol fromString(String text) {
		if (text != null) {
			for (BaProtocol p : BaProtocol.values()) {
				if (text.equals(p.text)) {
					return p;
				}
			}
		}
		throw new IllegalArgumentException("No protocol of " + text + "defined in wsat coordiantion type");
	}

	@Override
	public String toString() {
		return text;
	}

	public String getText() {
		return text;
	}

}
