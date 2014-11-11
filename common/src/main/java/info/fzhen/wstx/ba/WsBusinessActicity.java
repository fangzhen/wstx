package info.fzhen.wstx.ba;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WsTransaction;

/**
 * business activity instance for application to begin, commit...
 * an activity
 */
public class WsBusinessActicity extends WsTransaction {
	/** coordination type of the activity. {@link CoordinationType.WSBA_ATOMIC}
	 * or {@link  CoordinationType.WSBA_MIXED} */
	private CoordinationType type;
	@Override
	public void begin() {

	}

	public CoordinationType getType() {
		return type;
	}

	public void setType(CoordinationType type) {
		this.type = type;
	}
}
