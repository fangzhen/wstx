package info.fzhen.wstx.util;

import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.cxf.interceptor.WstxTransform;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.headers.Header;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

public class MsgContextUtil {
	private static final Log __LOG = LogFactory.getLog(MsgContextUtil.class);

	/**
	 * Retrieve private Id that identifies target activity.
	 *
	 * @param webServiceContext
	 * @return
	 */
	public static String retrievePrivateId(WebServiceContext webServiceContext) {
		MessageContext msgc = webServiceContext.getMessageContext();
		AddressingProperties maps = (AddressingProperties) msgc.get(JAXWSAConstants.SERVER_ADDRESSING_PROPERTIES_INBOUND);
		List<Object> referenceParas = maps.getToEndpointReference().getReferenceParameters().getAny();
		for (Object para : referenceParas) {
			//TODO should remove
			// backward compatibility
			if (para instanceof JAXBElement) {
				@SuppressWarnings("unchecked")
				JAXBElement<Object> jele = (JAXBElement<Object>) para;
				if (jele.getName().getLocalPart().equalsIgnoreCase(PrivateIdType.class.getSimpleName())) {
					//CXF MAPCodec pays special attention to String. As a result, our PrivateIdType is unmarshalled as String instead of Element
					String id = (String) jele.getValue();
					return id;
				}
			} else if (para instanceof PrivateIdType) {
				return ((PrivateIdType) para).getPrivateId();
			}
		}
		return null;
	}

	/**
	 * Retrieve participant protocol service EPR from inbound registration message.
	 *
	 * @param webServiceContext
	 * @return
	 */
	public static EndpointReferenceType retrieveParticipantEpr(WebServiceContext webServiceContext) {
		MessageContext msgc = webServiceContext.getMessageContext();
		AddressingProperties maps = (AddressingProperties) msgc.get(JAXWSAConstants.SERVER_ADDRESSING_PROPERTIES_INBOUND);
		return null;
	}

	/**
	 * Retrieve coordination context of current activity from an application message.
	 *
	 * @param webServiceContext
	 * @return
	 */
	public static CoordinationContext retrieveCoorCtx(WebServiceContext webServiceContext) {
		List<Header> headers = (List<Header>) webServiceContext.getMessageContext().get(Header.HEADER_LIST);
		QName tname = new QName(WstxTransform.Wstx200606.WSCOOR_NAMESPACE_URI, CoordinationContext.class.getSimpleName());
		CoordinationContext ctx = null;
		for (Header header : headers) {
			if (header.getName().equals(tname)) {
				ctx = (CoordinationContext) header.getObject();
				break;
			}
		}
		if (ctx == null) {
			if (__LOG.isWarnEnabled()) {
				__LOG.warn("Cannot retrieve coordination context from incoming message");
			}
			;
		}
		return ctx;
	}
}
