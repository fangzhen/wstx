package info.fzhen.wstx;

/**
 * Coordination Type enumerations that are supported
 */
public enum CoordinationType {
	WSAT("http://docs.oasis-open.org/ws-tx/wsat/2006/06"),
	WSBA_ATOMIC("http://docs.oasis-open.org/ws-tx/wsba/2006/06/AtomicOutcome"),
	WSBA_MIXED("http://docs.oasis-open.org/ws-tx/wsba/2006/06/MixedOutcome");

	private String text;

	private CoordinationType(String identifier) {
		this.text = identifier;
	}

	public static CoordinationType fromString(String identifier) {
		if (identifier != null) {
			for (CoordinationType ct : CoordinationType.values()) {
				if (identifier.equals(ct.text)) {
					return ct;
				}
			}
		}
		throw new IllegalArgumentException("No coordination type " + identifier + " found");
	}

	@Override
	public String toString() {
		return text;
	}

	public String getText() {
		return text;
	}
}