package info.fzhen.wstx.context;

import info.fzhen.wstx.config.CoorEprConfig;
import info.fzhen.wstx.coordinator.CoordinatorManager;
import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.JAXBUtils;

import java.util.List;

import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.ReferenceParametersType;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
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

		//TODO delete the output
		//		DOMSource doms = (DOMSource) EndpointReferenceUtils
		//				.convertToXML(registrationEPR);
		//		System.out.println("*********AbstractActivityCoordinatorContext"
		//				+ JAXBUtils.getStringFromDomSource(doms));

		//TODO re-implement EndpointReferenceType to avoid cxf dependency 
		EndpointReferenceType registrationEPR = new EndpointReferenceType();
		String regAddr = eprConf.getRegistrationService();
		AttributedURIType addr = new AttributedURIType();
		addr.setValue(regAddr);
		registrationEPR.setAddress(addr);

		ReferenceParametersType ref = new ReferenceParametersType();
		List<Object> paras = ref.getAny();
		PrivateIdType pi = new PrivateIdType();
		pi.setPrivateId(privateId);
		JAXBUtils.addAsW3cElement(paras, PrivateIdType.class, pi);
		registrationEPR.setReferenceParameters(ref);

		W3CEndpointReference w3cRegistratoiERP = new W3CEndpointReference(
				EndpointReferenceUtils.convertToXML(registrationEPR));
		coordinationContext.setRegistrationService(w3cRegistratoiERP);
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
