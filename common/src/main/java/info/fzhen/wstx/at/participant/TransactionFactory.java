package info.fzhen.wstx.at.participant;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;

/**
 * stateless
 *
 * @author fangzhen
 */
public class TransactionFactory {
	private static TransactionFactory instance;

	private TransactionFactory() {

	}

	public static TransactionFactory getInstance() {
		synchronized (TransactionFactory.class){
			if (instance == null) {
				instance = new TransactionFactory();
			}
		}
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

}
