package info.fzhen.wstx.test.services;

import info.fzhen.wstx.at.twopc.At2pcPartManager;
import info.fzhen.wstx.test.ClientProxies;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.jws.WebService;

/**
 * Demo service that uses subordinate
 */
@WebService(serviceName = "helloServiceSubordinate")
public class HelloServiceSubordinate extends TransactionalService implements HelloService{
	private static final Log __LOG = LogFactory.getLog(HelloServiceSubordinate.class);
	private ClientProxies clientProxies = ClientProxies.getInstance();

	@Override
	public String sayHello() {
		String helloStr = "hello service with its own subordinate";
		if (__LOG.isDebugEnabled()){
			__LOG.debug("hello service with its own subordinate invoked");
		}
		CoordinationContext ctx = getCoordinationContext();
		if (ctx != null) {
			//use its own coordinator and get the new ctx from current ctx;
			ActivationPortType activationSer = clientProxies.getActivationService();
			CoordinationContext nctx = createNewCoordinationContext(activationSer, ctx);

			HelloD2pcPartcipant part = new HelloD2pcPartcipant();
			At2pcPartManager d2pdManager = At2pcPartManager.getInstance();
			d2pdManager.registerDurable2Pc(nctx, part);
			part.log();
		}
		return helloStr;
	}
}
