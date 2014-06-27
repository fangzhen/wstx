package info.fzhen.wstx.context;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * Activity instance
 * @author fangzhen
 *
 */
public interface ActivityCoordinatorContext {
	/**
	 * build coordination context defined by WS-C
	 * @return
	 */
	CoordinationContext buildCoordinationContext();

	/**
	 * Register participant
	 * @param registerPara
	 * @return Register Response
	 */
	RegisterResponseType register(RegisterType registerPara);
}
