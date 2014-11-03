package info.fzhen.wstx.test.services;

import info.fzhen.wstx.util.MsgContextUtil;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.ActivationPortType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CreateCoordinationContextType;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

/**
 * Provide transaction support for services
 */
public abstract class TransactionalService {
	@Resource
	private WebServiceContext wsContext;

	/**
	 * use its own coordinator
	 * @param activationService
	 * @param current current coordination context
	 * @return created coordination context using current
	 */
	public CoordinationContext createNewCoordinationContext(ActivationPortType activationService, CoordinationContext current){
		CreateCoordinationContextType ccct = new CreateCoordinationContextType();
		ccct.setCurrentContext(current);
		CreateCoordinationContextResponseType response = activationService.createCoordinationContextOperation(ccct);
		CoordinationContext nctx = response.getCoordinationContext();
		return nctx;
	}

	/**
	 * Retrieve transaction coordination context.
	 * @return current coordination context, or null if no coordination context
	 */
	public CoordinationContext getCoordinationContext(){
		return MsgContextUtil.retrieveCoorCtx(wsContext);
	}
}
