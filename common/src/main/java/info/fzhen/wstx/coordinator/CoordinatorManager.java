package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.at.AtomicTxCoordinator;
import info.fzhen.wstx.config.CoorEprConfig;
import info.fzhen.wstx.config.EprConfig;
import info.fzhen.wstx.context.ActivityCoordinatorContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

/**
 * Coordinator Manager that manages activities of this site.
 * @author fangzhen
 *
 */
public class CoordinatorManager {
	private static CoordinatorManager instance;
	/**
	 * Activities managed by this coordinator, private id as key.
	 */
	private Map<String, ActivityCoordinatorContext> activities = new HashMap<>();
	private CoorEprConfig coorEprConfiguration;
	/**
	 * Coordinator Eprs of different coordinator type.
	 */
	private Map<String, EprConfig> typeCoorEprConfigs = new HashMap<>();

	public static CoordinatorManager getInstance() {
		return instance;
	}

	public static void setInstance(CoordinatorManager instance) {
		CoordinatorManager.instance = instance;
	}

	public ActivityCoordinatorContext createActivityCoordinatorContext(CreateCoordinationContextType ccc){
		String pirvateId = genPrivateId();
		ActivityCoordinatorContext cctx = null;
		CoordinationType type = CoordinationType.fromString(ccc.getCoordinationType());
		switch (type) {
		case WSAT:
			cctx = AtomicTxCoordinator.createInstance(ccc, pirvateId);
		case WSBA:
			break;
		default:
			break;
		}
		activities.put(pirvateId, cctx);
		return cctx;
	}

	private String genPrivateId() {
		// TODO should ensure it to be unique. simply random
		return ""+new Random().nextInt();
	}

	public CoorEprConfig getCoorEprConfiguration() {
		return coorEprConfiguration;
	}

	public void setCoorEprConfiguration(CoorEprConfig eprConfiguration) {
		this.coorEprConfiguration = eprConfiguration;
	}
	
	public ActivityCoordinatorContext getActivity(String id){
		return activities.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EprConfig> T getTypeCoorEprConfig(String type){
		return (T)typeCoorEprConfigs.get(type);
	}
	public void setTypeCoorEprConfigs(Map<String, EprConfig> typeCoorEprConfigs) {
		this.typeCoorEprConfigs = typeCoorEprConfigs;
	}

}
