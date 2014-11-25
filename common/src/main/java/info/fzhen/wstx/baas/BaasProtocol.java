package info.fzhen.wstx.baas;

/**
 * Business activity with abstract service coordination type
 * {@link info.fzhen.wstx.CoordinationType#WSBAAS} protocol
 */
public enum BaasProtocol {
	COORDINATION_COMPLETION("http://www.fzhen.info/ws-tx/wsbaas/2014/11/CoordinatorCompletion"),
	COMPLETION("http://www.fzhen.info/ws-tx/wsbaas/Completion");

	private String text;

	private BaasProtocol(String text) {
		this.text = text;
	}

	public static BaasProtocol fromString(String text) {
		if (text != null) {
			for (BaasProtocol p : BaasProtocol.values()) {
				if (text.equals(p.text)) {
					return p;
				}
			}
		}
		throw new IllegalArgumentException("No protocol of " + text + "defined in wsbaas coordination type");
	}

	@Override
	public String toString() {
		return text;
	}

	public String getText() {
		return text;
	}

}
