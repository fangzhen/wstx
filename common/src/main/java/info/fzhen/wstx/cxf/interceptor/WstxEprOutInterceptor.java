package info.fzhen.wstx.cxf.interceptor;

import info.fzhen.wstx.coordinator.PrivateIdType;
import info.fzhen.wstx.util.JAXBUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.*;
import org.apache.cxf.ws.addressing.soap.MAPCodec;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Soap interceptor responsible for encoding referenced parameters
 */
public class WstxEprOutInterceptor extends AbstractSoapInterceptor {
	private static final Log __LOG = LogFactory.getLog(WstxEprOutInterceptor.class);

	public WstxEprOutInterceptor() {
		super(Phase.PRE_PROTOCOL);
		addBefore(MAPCodec.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		if (!MessageUtils.getContextualBoolean(message, MAPAggregator.ADDRESSING_DISABLED, false)) {
			if (ContextUtils.isOutbound(message)) {
				encodeRefParas(message, ContextUtils.retrieveMAPs(message, false, true));
			} else {
				if (__LOG.isErrorEnabled()) {
					__LOG.error("the interceptor can only be used as out interceptor");
				}
			}
		}
	}

	private void encodeRefParas(SoapMessage message, AddressingProperties maps) {
		EndpointReferenceType toEpr = maps.getToEndpointReference();
		ReferenceParametersType refs = toEpr.getReferenceParameters();
		if (refs == null) return;

		List<Element> replacement = new ArrayList<>(2);
		Iterator<Object> itor = refs.getAny().iterator();
		while (itor.hasNext()) {
			Object o = itor.next();
			if (o instanceof PrivateIdType) {
				Element ele = JAXBUtils.marshal2Element(PrivateIdType.class, o);
				if (ele != null) {
					replacement.add(ele);
					itor.remove();
				} else {
					if (__LOG.isWarnEnabled()) {
						__LOG.warn("faild to marshal object of type " + o.getClass() + "into w3c Element");
					}
				}
			}
		}
		refs.getAny().addAll(replacement);
	}
}
