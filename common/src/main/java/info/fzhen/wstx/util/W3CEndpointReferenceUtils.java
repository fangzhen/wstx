package info.fzhen.wstx.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.wsdl.EndpointReferenceUtils;
import org.w3c.dom.Node;

public class W3CEndpointReferenceUtils {
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
	 * 
	 * @param address
	 * @param paras referenced parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static W3CEndpointReference createInstance(String address, Object paras){
		EndpointReferenceType cxfEpr = new EndpointReferenceType();
		AttributedURIType addr = new AttributedURIType();
		cxfEpr.setAddress(addr);
		addr.setValue(address);
		JAXBUtils.addAsW3cElement(cxfEpr.getAny(), (Class<Object>)paras.getClass(), paras);
		W3CEndpointReference w3cEpr = new W3CEndpointReference(
				EndpointReferenceUtils.convertToXML(cxfEpr));
		return w3cEpr;
	}
}
