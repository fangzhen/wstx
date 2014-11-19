package info.fzhen.wstx.test.services;

import info.fzhen.wstx.at.twopc.At2pcPartManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.jws.WebService;

@WebService(serviceName = "helloService")
public class HelloServiceImpl extends TransactionalService implements HelloService {
	private static final Log __LOG = LogFactory.getLog(HelloServiceImpl.class);

	@Override
	public String sayHello() {
		String helloStr = "Hello";
		CoordinationContext ctx = getCoordinationContext();
		assert ctx != null;
		if (ctx != null) { //coordination context are found, do registration now
			HelloD2pcPartcipant part = new HelloD2pcPartcipant();
			At2pcPartManager d2pdManager = At2pcPartManager.getInstance();
			d2pdManager.registerDurable2Pc(ctx, part);
			part.log();
		}
		return helloStr;
	}
}