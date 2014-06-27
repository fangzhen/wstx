package info.fzhen.wstx.wscoor;

import info.fzhen.wstx.service.RegistrationService;
import info.fzhen.wstx.util.MsgContextUtil;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

@WebService(endpointInterface="org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType")
public class RegistrationPort implements RegistrationPortType{
	@Resource
	private WebServiceContext wsContext;
	
	@Override
	public RegisterResponseType registerOperation(RegisterType parameters) {
		String pid = MsgContextUtil.retrievePrivateId(wsContext);
		return new RegistrationService().registerOperation(parameters, pid);
	}
}
