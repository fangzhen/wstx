package info.fzhen.wstx.cxf.interceptor;

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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 */
public class WstxEprInInterceptor extends AbstractSoapInterceptor {
	private static final Log __LOG = LogFactory.getLog(WstxEprInInterceptor.class);

	public WstxEprInInterceptor() {
		super(Phase.PRE_PROTOCOL);
		addAfter(MAPCodec.class.getName());
	}


	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		if (ContextUtils.isOutbound(message)) {
			if (__LOG.isErrorEnabled()) {
				__LOG.error("this interceptor should only be used as in interceptor");
			}
			return;
		}
		if (!MessageUtils.getContextualBoolean(message, MAPAggregator.ADDRESSING_DISABLED, false)) {
			decodeRefParas(message);
		}
	}

	private void decodeRefParas(SoapMessage message) {
		AddressingProperties maps = ContextUtils.retrieveMAPs(message, false, false);
		if (maps != null) {
			EndpointReferenceType toEpr = maps.getToEndpointReference();
			if (toEpr != null) {
				ReferenceParametersType refs = toEpr.getReferenceParameters();
				if (refs == null) return;
				List<Object> replacement = new ArrayList<>();
				Iterator<Object> itor = refs.getAny().iterator();
				while (itor.hasNext()) {
					Object o = itor.next();
					if (o instanceof Element) {
						Element ele = (Element) o;
						String headerNsp = ele.getNamespaceURI();
						if (WstxTransform.isSupport(headerNsp)) {
							JAXBContext jaxbctx;
							try {
								jaxbctx = WstxTransform.getJAXBContext(headerNsp);
								Unmarshaller unmarshaller = jaxbctx.createUnmarshaller();
								Object unmared = unmarshaller.unmarshal(ele);
								Object obj = unmared;
								if (unmared instanceof JAXBElement) {
									@SuppressWarnings("rawtypes")
									JAXBElement element = (JAXBElement) unmared;
									obj = element.getValue();
								}
								itor.remove();
								replacement.add(obj);
								if (__LOG.isDebugEnabled()) {
									__LOG.debug("unmarshalled referenced parameter of type " + obj.getClass());
								}
							} catch (JAXBException e) {
								if (__LOG.isWarnEnabled()) {
									__LOG.warn("Failed when decode Ws-coor or Ws-Tx soap header due"
											+ "to jaxbExceptions");
								}
								if (__LOG.isDebugEnabled()) {
									__LOG.debug(e);
								}
								e.printStackTrace();
							}
						}
					}
				}
				refs.getAny().addAll(replacement);
			}
		}
	}

}
