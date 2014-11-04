package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.at.AtomicTxSubordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;

/**
 * Subordinate manager. It use {@link info.fzhen.wstx.at.twopc.At2pcCoorManager} and
 * {@link info.fzhen.wstx.at.twopc.At2pcPartManager} to act as coordinator side and
 * participant side manager respectively.
 */
public class At2pcSubordinateManager {
	private static final Log __LOG = LogFactory.getLog(At2pcSubordinateManager.class);

	private static At2pcSubordinateManager instance;

	private At2pcCoorManager coorManager;
	private At2pcPartManager partManager;

	public static At2pcSubordinateManager getInstance() {
		if (instance == null) {
			String msg = "2PC protocol service subordiante manager " + At2pcSubordinateManager.class.getSimpleName() +
						" hasn't been initialized. It should be initiated " +
						"by Spring as singleton. Please check your conf file.";
			if (__LOG.isErrorEnabled()) {
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}

	public static void setInstance(At2pcSubordinateManager instance) {
		At2pcSubordinateManager.instance = instance;
	}
	/**
	 * forward volatile 2PC registration
	 * @param activity
	 * @param registerPara
	 */
	public RegisterResponseType registerVolatile2Pc(
			AtomicTxSubordinate activity, RegisterType registerPara) {
		RegisterResponseType response = register2Pc(activity, registerPara);
		return response;
	}

	/**
	 * forward durable 2PC registration
	 * @param activity
	 * @param registerPara
	 */
	public RegisterResponseType registerDurable2Pc(AtomicTxSubordinate activity,
												   RegisterType registerPara) {
		RegisterResponseType response = register2Pc(activity, registerPara);
		return response;
	}

	public RegisterResponseType register2Pc(AtomicTxSubordinate activity,
											RegisterType registerPara) {
		At2pcSubordinateService subordinateService = new At2pcSubordinateService();
		partManager.registerSub2Pc(subordinateService.partService, activity, registerPara);

		RegisterResponseType response = coorManager.registerSub2Pc(
				subordinateService.coorService, activity, registerPara);
		return response;
	}

	public void setCoorManager(At2pcCoorManager coorManager) {
		this.coorManager = coorManager;
	}

	public void setPartManager(At2pcPartManager partManager) {
		this.partManager = partManager;
	}
}