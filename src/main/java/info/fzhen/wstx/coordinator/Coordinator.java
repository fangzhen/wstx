package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.CoordinationType;
import info.fzhen.wstx.at.ATActivityCoordinatorContext;
import info.fzhen.wstx.context.ActivityCoordinatorContext;
import info.fzhen.wstx.context.CoordinatorContext;
import info.fzhen.wstx.util.EPRConfiguration;

import java.util.List;
import java.util.Random;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.ReferenceParametersType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

public class Coordinator {
	private static Coordinator instance;
	
	public static Coordinator getInstance() {
		return instance;
	}

	public static void setInstance(Coordinator instance) {
		Coordinator.instance = instance;
	}

	private CoordinatorContext coordinatorContext;

	public ActivityCoordinatorContext createActivityCoordinatorContext(CreateCoordinationContextType ccc){
		switch (ccc.getCoordinationType()) {
		case CoordinationType.WSAT:
			ATActivityCoordinatorContext activityCoordinatorContext;
			activityCoordinatorContext = new ATActivityCoordinatorContext();
			activityCoordinatorContext.setCoordinationType(ccc.getCoordinationType());
			if (ccc.getExpires() != null){
				activityCoordinatorContext.setExpires(ccc.getExpires().getValue());
			}

			EndpointReferenceType registrationEPR = new EndpointReferenceType();
			EPRConfiguration eprConf = coordinatorContext.getEprConfiguration();
			String regAddr = eprConf.getHost() + eprConf.getRegistrationService();
			AttributedURIType addr = new AttributedURIType();
			addr.setValue(regAddr);
			registrationEPR.setAddress(addr);
			String shortId = getUniqueNum();

			ReferenceParametersType ref = new ReferenceParametersType();
			List<Object> paras = ref.getAny();
//			paras.add(regAddr);
			registrationEPR.setReferenceParameters(ref);//TODO referenceParameters did not set
			activityCoordinatorContext.setRegistrationEPR(registrationEPR);
			
			String identifier = eprConf.getHost() + shortId;
			activityCoordinatorContext.setIdentifier(identifier);
			return activityCoordinatorContext;
		case CoordinationType.WSBA:
			break;
		default:
			break;
		}
		return null;
	}

	private String getUniqueNum() {
		// TODO should ensure it to be unique. simply random
		return ""+new Random().nextInt();
	}

	public CoordinatorContext getCoordinatorContext() {
		return coordinatorContext;
	}

	public void setCoordinatorContext(CoordinatorContext coordinatorContext) {
		this.coordinatorContext = coordinatorContext;
	}

}
