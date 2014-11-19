package info.fzhen.wstx.test.services;

import info.fzhen.wstx.ba.cc.CcParticipant;
import info.fzhen.wstx.ba.cc.CcParticipantProtocolMgr;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.jws.WebService;

/**
 * Hello service that with a business activity participant
 */
@WebService
public class HelloServiceBa extends TransactionalService implements HelloService{
	@Override
	public String sayHello() {
		String helloStr = "Hello business activity";
		CoordinationContext ctx = getCoordinationContext();
		assert ctx != null;
		if (ctx != null) { //coordination context are found, do registration now
			CcParticipant part = new HelloBaCcParticipant();
			CcParticipantProtocolMgr mgr = CcParticipantProtocolMgr.getInstance();
			mgr.registerBacc(ctx, part);
//			part.dosomething();
		}
		return helloStr;
	}
}
