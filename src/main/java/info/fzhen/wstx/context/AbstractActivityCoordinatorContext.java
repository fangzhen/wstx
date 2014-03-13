package info.fzhen.wstx.context;

import java.util.List;

import info.fzhen.wstx.coordinator.CoordinatorContext;
import info.fzhen.wstx.coordinator.PrivateInstanceType;
import info.fzhen.wstx.util.EPRConfiguration;
import info.fzhen.wstx.util.JAXBUtils;

import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.ReferenceParametersType;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContextType.Identifier;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.Expires;

public class AbstractActivityCoordinatorContext implements
		ActivityCoordinatorContext {
//	protected EndpointReferenceType registrationEPR;
	protected CoordinatorContext coordinatorContext;

	protected String identifier; //identifier without host
	protected long expires;
	protected String coordinationType;

	@Override
	public CoordinationContext buildCoordinationContext() {
		CoordinationContext coordinationContext = new CoordinationContext();
		coordinationContext.setCoordinationType(coordinationType);
		Expires e = new Expires();
		e.setValue(expires);
		coordinationContext.setExpires(e);

		EPRConfiguration eprConf = coordinatorContext.getEprConfiguration();

		Identifier id = new Identifier();
		id.setValue(eprConf.getHost() + identifier);
		coordinationContext.setIdentifier(id);

		//TODO delete the output
//		DOMSource doms = (DOMSource) EndpointReferenceUtils
//				.convertToXML(registrationEPR);
//		System.out.println("*********AbstractActivityCoordinatorContext"
//				+ JAXBUtils.getStringFromDomSource(doms));
		
		//TODO re-implement EndpointReferenceType to avoid cxf dependency 
		EndpointReferenceType registrationEPR = new EndpointReferenceType();
		String regAddr = eprConf.getHost() + eprConf.getRegistrationService();
		AttributedURIType addr = new AttributedURIType();
		addr.setValue(regAddr);
		registrationEPR.setAddress(addr);

		ReferenceParametersType ref = new ReferenceParametersType();
		List<Object> paras = ref.getAny();
		PrivateInstanceType pi = new PrivateInstanceType();
		pi.setValue(identifier);
		JAXBUtils.addAsW3cElement(paras, PrivateInstanceType.class, pi);
		registrationEPR.setReferenceParameters(ref);

		W3CEndpointReference w3cRegistratoiERP = new W3CEndpointReference(
				EndpointReferenceUtils.convertToXML(registrationEPR));
		coordinationContext.setRegistrationService(w3cRegistratoiERP);
		return coordinationContext;
	}


	public CoordinatorContext getCoordinatorContext() {
		return coordinatorContext;
	}
	
	public void setCoordinatorContext(CoordinatorContext coordinatorContext) {
		this.coordinatorContext = coordinatorContext;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
