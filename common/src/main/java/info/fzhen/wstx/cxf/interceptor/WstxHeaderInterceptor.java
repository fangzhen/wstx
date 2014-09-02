package info.fzhen.wstx.cxf.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.ContextUtils;
import org.apache.cxf.ws.addressing.soap.MAPCodec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.*;
import javax.xml.transform.dom.DOMResult;
import java.util.List;

/**
 * WS-C/Tx in interceptor to decode WS-Coordination and Ws-Tx soap header
 * @author fangzhen
 *
 */
public class WstxHeaderInterceptor extends AbstractSoapInterceptor{
	public static final Log __LOG = LogFactory.getLog(WstxHeaderInterceptor.class);

	public WstxHeaderInterceptor(){
		super(Phase.PRE_PROTOCOL);
        addBefore(MAPCodec.class.getSimpleName());
	}
	public WstxHeaderInterceptor(String phase){
		super(phase);
	}

    @Override
	public void handleMessage(SoapMessage message) throws Fault {
        if (ContextUtils.isOutbound(message)) {
            marshalWstxHeader(message);
        }else{
            unmarshalWstxHeader(message);
        }
    }
    private void unmarshalWstxHeader(SoapMessage message){
        List<Header> headers = message.getHeaders();
        for (Header header : headers) {
            if (header.getObject() instanceof Element) {
                Element ele = (Element) header.getObject();
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
                        header.setObject(obj);
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
    }
    private void marshalWstxHeader(SoapMessage message){
        List<Header> headers = message.getHeaders();
        for (Header header : headers){
            if (!(header.getObject() instanceof Element)){
                Object obj = header.getObject();
                String headerNsp = header.getName().getNamespaceURI();
                if (WstxTransform.isSupport(headerNsp)){
                    JAXBContext jaxbContext;
                    try{
                        jaxbContext = WstxTransform.getJAXBContext(headerNsp);
                        Marshaller marshaller = jaxbContext.createMarshaller();
                        DOMResult domResult = new DOMResult();
                        marshaller.marshal(obj, domResult);
                        Element ele = ((Document)domResult.getNode()).getDocumentElement();
                        header.setObject(ele);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
