package info.fzhen.wstx.test.services;

import info.fzhen.wstx.transaction.WsatDurable2PcManager;
import info.fzhen.wstx.util.MsgContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class HelloServiceImpl implements HelloService{
	private static final Log __LOG = LogFactory.getLog(HelloServiceImpl.class);
	
	@Resource
	private WebServiceContext wsContext;

	public String sayHello() {
		String helloStr = "Hello";
		CoordinationContext ctx = MsgContextUtil.retrieveCoorCtx(wsContext);
		if (ctx != null){ //coordination context are found, do registration now
            HelloD2pcPartcipant part = new HelloD2pcPartcipant();
            WsatDurable2PcManager d2pdManager = WsatDurable2PcManager.getInstance();
            d2pdManager.registerDurable2Pc(ctx, part);
            part.log();
		}
		return helloStr;
	}
}