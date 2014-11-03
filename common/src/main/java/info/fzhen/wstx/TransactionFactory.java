package info.fzhen.wstx;

import info.fzhen.wstx.at.WsatTransaction;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

/**
 * stateless
 *
 * @author fangzhen
 */
public class TransactionFactory {
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

	public <T extends WsTransaction> T createSubTransaction(ActivationPortType activationSer, CoordinationContext ctx) {
		return null;
	}
}
