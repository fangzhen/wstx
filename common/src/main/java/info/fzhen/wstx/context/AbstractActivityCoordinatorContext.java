package info.fzhen.wstx.context;

import info.fzhen.wstx.config.CoorEprConfig;
import info.fzhen.wstx.coordinator.CoordinatorManager;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.EprUtils;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContextType.Identifier;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.Expires;

public abstract class AbstractActivityCoordinatorContext implements
		ActivityCoordinatorContext {
	protected CoordinatorManager coordinatorManager;

	protected String privateId; //privateId without host
	protected long expires;
	protected String coordinationType;

	public AbstractActivityCoordinatorContext(){
		this.coordinatorManager = CoordinatorManager.getInstance();
	}

    /**
     * Build coordination context for the activity.
     * @return coordination context
     */
	@Override
	public CoordinationContext buildCoordinationContext() {
		CoordinationContext coordinationContext = new CoordinationContext();
		coordinationContext.setCoordinationType(coordinationType);
		Expires e = new Expires();
		e.setValue(expires);
		coordinationContext.setExpires(e);

		CoorEprConfig eprConf = coordinatorManager.getCoorEprConfiguration();
		Identifier id = new Identifier();
		id.setValue(eprConf.getPrefix() + "/" + privateId);
		coordinationContext.setIdentifier(id);

		String regAddr = eprConf.getRegistrationService();
        EndpointReferenceType registrationEPR = EprUtils.createCxfEprInstance(regAddr, new PrivateIdType(privateId));

		coordinationContext.setRegistrationService(registrationEPR);
		return coordinationContext;
	}

	public String getPrivateId() {
		return privateId;
	}

	public void setPrivateId(String pid) {
		this.privateId = pid;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public String getCoordinationType() {
		return coordinationType;
	}

	public void setCoordinationType(String coordinationType) {
		this.coordinationType = coordinationType;
	}
}
