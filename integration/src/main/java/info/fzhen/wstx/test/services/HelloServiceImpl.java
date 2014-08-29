package info.fzhen.wstx.test.services;

import info.fzhen.wstx.cxf.interceptor.WstxTransform;
import info.fzhen.wstx.util.MsgContextUtil;

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
		CoordinationContext ctx = MsgContextUtil.retrieveCoorCtx(wsContext);
		if (ctx != null){ //coordination context are found, do registration now
			//TODO

		}
		return helloStr;
	}
}