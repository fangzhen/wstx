package info.fzhen.wstx.test.services;

import info.fzhen.wstx.cxf.interceptor.WstxTransform;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.headers.Header;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

@WebService
public class HelloServiceImpl implements HelloService{
	private static final Log __LOG = LogFactory.getLog(HelloServiceImpl.class);
	
	@Resource
	private WebServiceContext wsContext;

	public String sayHello() {
		String helloStr = "Hello";
		List<Header> headers = (List<Header>) wsContext.getMessageContext().get(Header.HEADER_LIST);
		CoordinationContext ctx = null;
		QName tname = new QName(WstxTransform.Wstx200606.WSCOOR_NAMESPACE_URI, CoordinationContext.class.getSimpleName());
		for (Header header : headers){
			if (header.getName().equals(tname)){
				ctx = (CoordinationContext)header.getObject();
				break;
			}
		}		
		if (ctx == null){
			if (__LOG.isWarnEnabled()){
				__LOG.warn("Cannot retrieve coordination context from incoming message");
			};
		}
		return helloStr;
	}

}
