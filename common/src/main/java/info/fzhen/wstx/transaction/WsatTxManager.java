package info.fzhen.wstx.transaction;

import info.fzhen.wstx.Constants;
import info.fzhen.wstx.config.ATPartEprConfig;
import info.fzhen.wstx.participant.at.ATInitiator;
import info.fzhen.wstx.util.W3CEndpointReferenceUtils;

import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.apache.cxf.ws.addressing.impl.AddressingPropertiesImpl;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

/**
 * Global transaction manager of the site. It also holds global context.
 * 
 * @author fangzhen
 * 
 */
public class WsatTxManager {
	private static WsatTxManager instance;
	private ATPartEprConfig eprConfiguration;
	
	public static WsatTxManager getInstance() {
		return instance;
	}

	public static void setInstance(WsatTxManager instance) {
		WsatTxManager.instance = instance;
	}

	public void registerInitiator(ATInitiator initiator,
			WsatTransaction transaction) {
		transaction.setInitiator(initiator);

		EndpointReferenceType initiatorEprCXF = new EndpointReferenceType();
		AttributedURIType addr = new AttributedURIType();
		addr.setValue(eprConfiguration.getInitiatorAddress());
		initiatorEprCXF.setAddress(addr);
		Source src = EndpointReferenceUtils.convertToXML(initiatorEprCXF);
		W3CEndpointReference initiatorEpr = new W3CEndpointReference(src);

		RegisterType reg = new RegisterType();
		reg.setParticipantProtocolService(initiatorEpr);
		reg.setProtocolIdentifier(Constants.WSATType.COMPLETION_PROTOCOL);

		W3CEndpointReference regSer = transaction.getCoordinationContext()
				.getRegistrationService();
		EndpointReferenceType regSerCXF = W3CEndpointReferenceUtils.convertToCXFEpr(regSer);
		
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(RegistrationPortType.class);
		factory.setAddress(regSerCXF.getAddress().getValue());
		factory.getFeatures().add(new WSAddressingFeature());
		RegistrationPortType client = (RegistrationPortType) factory.create();
		AddressingProperties maps = new AddressingPropertiesImpl();
		maps.setTo(regSerCXF);
		 
		((BindingProvider)client).getRequestContext()
		    .put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, maps);
		
		RegisterResponseType response = client.registerOperation(reg);
		W3CEndpointReference coorInitiatorEpr = response.getCoordinatorProtocolService();
		transaction.setCoorInitiatorEpr(coorInitiatorEpr);
	}

	public void setEprConfiguration(ATPartEprConfig eprConfig) {
		this.eprConfiguration = eprConfig;
	}
}
