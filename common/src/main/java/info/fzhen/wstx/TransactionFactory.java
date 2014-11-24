package info.fzhen.wstx;

import info.fzhen.wstx.at.WsatTransaction;
import info.fzhen.wstx.ba.WsBusinessActicity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

/**
 * stateless
 *
 * @author fangzhen
 */
public class TransactionFactory {
	private static final Log __LOG = LogFactory.getLog(TransactionFactory.class);

	private static TransactionFactory instance = new TransactionFactory();

	private TransactionFactory() {

	}

	public static TransactionFactory getInstance() {
		return instance;
	}

	public static void setInstance(TransactionFactory instance) {
		TransactionFactory.instance = instance;
	}

	public WsatTransaction createWsatTransaction(ActivationPortType activationSer) {
		WsatTransaction transaction;
		transaction = new WsatTransaction();
		transaction.setActivationSer(activationSer);
		return transaction;
	}

	public WsBusinessActicity createAtomicBusinessActivity(
			ActivationPortType activationSer){
		return createBusinessActivity(activationSer, CoordinationType.WSBA_ATOMIC);
	}
	public WsBusinessActicity createMixedBusinessActivity(
			ActivationPortType activationSer){
		return createBusinessActivity(activationSer, CoordinationType.WSBA_MIXED);
	}

	private WsBusinessActicity createBusinessActivity(
			ActivationPortType activationSer, CoordinationType type){
		WsBusinessActicity acticity = new WsBusinessActicity();
		acticity.setActivationSer(activationSer);
		acticity.setType(type);
		if (type != CoordinationType.WSBA_ATOMIC &&
				type != CoordinationType.WSBA_MIXED){
			String msg = "Unsupported coordination type when creating Business activity" + type.getText()
			+ ". Must be " + CoordinationType.WSBA_ATOMIC.getText() +
			" or " + CoordinationType.WSBA_MIXED.getText();
			if (__LOG.isErrorEnabled()){
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return acticity;
	}
}
