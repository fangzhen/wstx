package info.fzhen.wstx.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import info.fzhen.wstx.coordinator.PrivateIdType;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.apache.cxf.ws.addressing.ReferenceParametersType;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.apache.cxf.ws.addressing.impl.AddressingPropertiesImpl;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
import org.w3c.dom.Node;

public class EprUtils {
	/**
	 * Convert a {@link W3cEndpointReference} to {@link EndpointReferenceType}
	 * @param w3cEpr
	 * @return
	 */
	public static EndpointReferenceType convertToCXFEpr(
			W3CEndpointReference w3cEpr) {
		EndpointReferenceType cxfEpr;
		DOMResult result = new DOMResult();
		w3cEpr.writeTo(result);
		Node node = result.getNode();
		JAXBContext jaxbContext;
		try {	
			jaxbContext = JAXBContext.newInstance(EndpointReferenceType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<EndpointReferenceType> root = jaxbUnmarshaller
					.unmarshal(node, EndpointReferenceType.class);
			cxfEpr = root.getValue();
		} catch (JAXBException e) {
			cxfEpr = null;
			e.printStackTrace();
		}
		return cxfEpr;
	}
	/**
	 * Create client proxy with ws-addressing feature.
	 * @param clazz
	 * @param epr
	 * @return
	 */
	public static <T> T createWsaddrClientProxy(Class<T> clazz, EndpointReferenceType epr){
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(clazz);
		factory.setAddress(epr.getAddress().getValue());
		factory.getFeatures().add(new WSAddressingFeature());
		@SuppressWarnings("unchecked")
		T client = (T) factory.create();
		AddressingProperties maps = new AddressingPropertiesImpl();
		maps.setTo(epr);

		((BindingProvider)client).getRequestContext()
		    .put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, maps);
		return client;
	}
	
	/**
	 * Create Instance of {@link EndpointReferenceType}
	 * @param address
	 * @param pid referenced privateid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static EndpointReferenceType createCxfEprInstance(String address, PrivateIdType pid){
		EndpointReferenceType cxfEpr = new EndpointReferenceType();
		AttributedURIType addr = new AttributedURIType();
		cxfEpr.setAddress(addr);
        addr.setValue(address);

		ReferenceParametersType refParas = new ReferenceParametersType();
		cxfEpr.setReferenceParameters(refParas);
        refParas.getAny().add(pid);
		//JAXBUtils.addAsW3cElement(cxfEpr.getReferenceParameters().getAny(), (Class<Object>)refPara.getClass(), refPara);
		return cxfEpr;
	}
	
	/**
	 * Create Instance of {@link W3CEndpointReference}
	 * @param address
	 * @param paras referenced parameters
	 * @return
	 */
	public static W3CEndpointReference createW3CEprInstance(String address, PrivateIdType paras){
		EndpointReferenceType cxfEpr = createCxfEprInstance(address, paras);
		W3CEndpointReference w3cEpr = new W3CEndpointReference(
				EndpointReferenceUtils.convertToXML(cxfEpr));
		return w3cEpr;
	}
}
