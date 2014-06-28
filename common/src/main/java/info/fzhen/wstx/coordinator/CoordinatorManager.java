package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.Constants;
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
	 * Activities managered by this coordinator, private id as key.
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
		switch (ccc.getCoordinationType()) {
		case Constants.WSAT:
			AtomicTxCoordinator activityCoordinatorContext;
			activityCoordinatorContext = new AtomicTxCoordinator();
			activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
			if (ccc.getExpires() != null){
				activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
			}
			String shortId = genPrivateId();
			activityCoordinatorContext.setPrivateId(shortId);
			//TODO initiate activityCoordinatorContext
			activities.put(shortId, activityCoordinatorContext);
			return activityCoordinatorContext;
		case Constants.WSBA:
			break;
		default:
			break;
		}
		return null;
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
	
	public EprConfig getTypeCoorEprConfig(String type){
		return typeCoorEprConfigs.get(type);
	}
	public void setTypeCoorEprConfigs(Map<String, EprConfig> typeCoorEprConfigs) {
		this.typeCoorEprConfigs = typeCoorEprConfigs;
	}

}
