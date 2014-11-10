package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.WstxRtException;
import info.fzhen.wstx.config.CoorEprConfig;
import info.fzhen.wstx.util.CommonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

import java.util.HashMap;
import java.util.Map;

/**
 * Coordinator Manager that manages activities of this site.
 * This is used in coordinator side.
 *
 * @author fangzhen
 */
public class CoordinatorManager {
	private static final Log __LOG = LogFactory.getLog(CoordinatorManager.class);

	private static CoordinatorManager instance;
	/**
	 * Activities managed by this coordinator, private id as key.
	 */
	private Map<String, ActivityCoordinatorContext> activities = new HashMap<>();
	private CoorEprConfig coorEprConfiguration;

	public static CoordinatorManager getInstance() {
		if (instance == null) {
			if (__LOG.isErrorEnabled()) {
				__LOG.error("Coordinator Manager hasn't been initialized");
			}
			throw new WstxRtException("Coordinator Manager hasn't been initialized");
		}
		return instance;
	}

	public static void setInstance(CoordinatorManager instance) {
		CoordinatorManager.instance = instance;
	}

	/**
	 * Factory method to create activities
	 * @param ccc
	 * @return
	 */
	public ActivityCoordinatorContext createActivityCoordinatorContext(CreateCoordinationContextType ccc) {
		String privateId = CommonUtils.genPrivateId();
		ActivityCoordinatorContext cctx = null;
		CoordinationType type;
		if (ccc.getCurrentContext() == null) {
			type = CoordinationType.fromString(ccc.getCoordinationType());
			switch (type) {
				case WSAT:
					cctx = new ActivityCoordinatorFactory().createAtActivity(ccc, privateId);
					break;
				case WSBA_ATOMIC:
					break;
				default:
					break;
			}
		}else{
			type = CoordinationType.fromString(ccc.getCurrentContext().getCoordinationType());
			switch (type){
				case WSAT:
					cctx = new ActivityCoordinatorFactory().createAtSubActivity(ccc, privateId);
					break;
				case WSBA_ATOMIC:
					break;
				default:
					break;
			}
		}
		activities.put(privateId, cctx);
		return cctx;
	}

	public CoorEprConfig getCoorEprConfiguration() {
		return coorEprConfiguration;
	}

	public void setCoorEprConfiguration(CoorEprConfig eprConfiguration) {
		this.coorEprConfiguration = eprConfiguration;
	}

	public ActivityCoordinatorContext getActivity(String id) {
		return activities.get(id);
	}
}
