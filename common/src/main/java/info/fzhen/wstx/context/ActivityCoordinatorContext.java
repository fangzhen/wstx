package info.fzhen.wstx.context;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

public interface ActivityCoordinatorContext {
	/**
	 * build coordination context defined by WS-C
	 * @return
	 */
	CoordinationContext buildCoordinationContext();
}
