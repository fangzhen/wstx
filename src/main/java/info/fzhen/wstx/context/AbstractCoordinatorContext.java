package info.fzhen.wstx.context;

import info.fzhen.wstx.util.EPRConfiguration;

import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContextType.Identifier;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.Expires;

public class AbstractCoordinatorContext implements CoordinatorContext {
	protected EndpointReferenceType registrationEPR;
	protected String identifier;
	protected long expires;
	protected String coordinationType;
	
	protected EPRConfiguration eprConfiguration;

	@Override
	public CoordinationContext buildCoordinationContext() {
		CoordinationContext coordinationContext;
		coordinationContext = new CoordinationContext();
		coordinationContext.setCoordinationType(coordinationType);
		Expires e = new Expires();
		e.setValue(expires);
		coordinationContext.setExpires(e);
		Identifier id = new Identifier();
		id.setValue(identifier);
		coordinationContext.setIdentifier(id);
		W3CEndpointReference w3cRegistratoiERP = new W3CEndpointReference(
				EndpointReferenceUtils.convertToXML(registrationEPR));
		coordinationContext.setRegistrationService(w3cRegistratoiERP);
		return null;
	}

	public EndpointReferenceType getRegistrationEPR() {
		return registrationEPR;
	}

	public void setRegistrationEPR(EndpointReferenceType registrationEPR) {
		this.registrationEPR = registrationEPR;
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
