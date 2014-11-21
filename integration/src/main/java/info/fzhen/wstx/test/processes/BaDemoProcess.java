package info.fzhen.wstx.test.processes;

import info.fzhen.wstx.TransactionFactory;
import info.fzhen.wstx.ba.WsBusinessActicity;
import info.fzhen.wstx.test.ClientProxies;
import info.fzhen.wstx.test.services.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

import javax.xml.ws.BindingProvider;

/**
 * Business activity process for testing
 */
public class BaDemoProcess extends TransactionalProcess implements Process{
	private static final Log __LOG = LogFactory.getLog(BaDemoProcess.class);

	private ClientProxies clientProxies = ClientProxies.getInstance();

	@Override
	public void execute() {
		if(__LOG.isInfoEnabled()){
			__LOG.info("Starting Demo process for business activity: "
					+ BaDemoProcess.class);
		}
		ActivationPortType activationSer = clientProxies.getActivationService();
		WsBusinessActicity activity = TransactionFactory.getInstance().
				createAtomicBusinessActivity(activationSer);
		activity.begin();

		HelloService helloService = clientProxies.getService("helloServiceBa");
		addTransactionInfo2Client((BindingProvider) helloService, activity);
		helloService.sayHello();

		activity.complete();
	}
}
