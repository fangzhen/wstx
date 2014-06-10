package info.fzhen.wstx.wscoor;

import javax.jws.WebService;

import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterResponseType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegisterType;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType;

@WebService(endpointInterface="org.oasis_open.docs.ws_tx.wscoor._2006._06.RegistrationPortType")
public class RegistrationPort implements RegistrationPortType{

	@Override
	public RegisterResponseType registerOperation(RegisterType parameters) {
		System.out.println("****RegisterResponseType#registerOperation");
		return new RegisterResponseType();
	}

}
