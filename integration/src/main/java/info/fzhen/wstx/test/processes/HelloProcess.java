package info.fzhen.wstx.test.processes;

import info.fzhen.wstx.TransactionFactory;
import info.fzhen.wstx.at.WsatTransaction;
import info.fzhen.wstx.at.completion.AtInitiatorPartManager;
import info.fzhen.wstx.test.ClientProxies;
import info.fzhen.wstx.test.services.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

import javax.jws.WebService;
import javax.xml.ws.BindingProvider;

@WebService
public class HelloProcess extends TransactionalProcess {
	private static final Log __log = LogFactory.getLog(TransactionalProcess.class);

	private ClientProxies clientProxies = ClientProxies.getInstance();

	public HelloProcess() {
	}

	@Override
	public void execute() {
		if (__log.isInfoEnabled()){
			__log.info("Testing hello process");
		}
		ActivationPortType activationSer = clientProxies.getActivationService();
		WsatTransaction transaction = TransactionFactory.getInstance().createWsatTransaction(activationSer);
		transaction.begin();

		//register WSAT completion protocol
		AtInitiatorPartManager manager = AtInitiatorPartManager.getInstance();
		manager.registerInitiator(transaction);

		//then send application messages with CC
		HelloService helloSer = clientProxies.getService("helloService");
		addTransactionInfo2Client((BindingProvider) helloSer, transaction);
		helloSer.sayHello();

		transaction.commit();
	}

}
