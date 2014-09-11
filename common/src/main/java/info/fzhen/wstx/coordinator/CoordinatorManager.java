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

	public ActivityCoordinatorContext createActivityCoordinatorContext(CreateCoordinationContextType ccc) {
		String pirvateId = CommonUtils.genPrivateId();
		ActivityCoordinatorContext cctx = null;
		CoordinationType type = CoordinationType.fromString(ccc.getCoordinationType());
		switch (type) {
			case WSAT:
				cctx = new ActivityCoordinatorFactory().createAtActivity(ccc, pirvateId);
				break;
			case WSBA:
				break;
			default:
				break;
		}
		activities.put(pirvateId, cctx);
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
