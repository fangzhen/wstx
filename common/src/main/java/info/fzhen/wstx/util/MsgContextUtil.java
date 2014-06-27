package info.fzhen.wstx.util;

import info.fzhen.wstx.coordinator.PrivateIdType;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

public class MsgContextUtil {
	/**
	 * Retrieve private Id that identifies target activity.
	 * @param webServiceContext
	 * @return
	 */
	public static String retrievePrivateId(WebServiceContext webServiceContext){
		MessageContext msgc = webServiceContext.getMessageContext();
		AddressingProperties maps = (AddressingProperties)msgc.get(JAXWSAConstants.SERVER_ADDRESSING_PROPERTIES_INBOUND);
		List<Object> referenceParas = maps.getToEndpointReference().getReferenceParameters().getAny();
		for (Object para : referenceParas){
			if (para instanceof JAXBElement){
				@SuppressWarnings("unchecked")
				JAXBElement<Object> jele = (JAXBElement<Object>)para;
				if (jele.getName().getLocalPart().equalsIgnoreCase(PrivateIdType.class.getSimpleName())){
					//CXF MAPCodec pays special attention to String. As a result, our PrivateIdType is unmarshalled as String instead of Element
					String id = (String)jele.getValue();
					return id;
				}
			}
		}
		return null;
	}
	/**
	 * Retrieve participant protocol service EPR from inbound registration message.
	 * @param webServiceContext
	 * @return
	 */
	public static EndpointReferenceType retrieveParticipantEpr(WebServiceContext webServiceContext){
		MessageContext msgc = webServiceContext.getMessageContext();
		AddressingProperties maps = (AddressingProperties)msgc.get(JAXWSAConstants.SERVER_ADDRESSING_PROPERTIES_INBOUND);
		return null;
	}
	
	/**
	 * Retrieve coordination context of current activity from an application message. 
	 * @param webServiceContext
	 * @return
	 */
	public static CoordinationContext retrieveCoorCtx(WebServiceContext webServiceContext){
		return null;
	}
}
