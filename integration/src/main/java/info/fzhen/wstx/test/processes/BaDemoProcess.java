package info.fzhen.wstx.test.processes;

import info.fzhen.wstx.TransactionFactory;
import info.fzhen.wstx.ba.WsBusinessActicity;
import info.fzhen.wstx.test.ClientProxies;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

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
		WsBusinessActicity acticity = TransactionFactory.getInstance().
				createAtomicBusinessActivity(activationSer);
		acticity.begin();
	}
}
