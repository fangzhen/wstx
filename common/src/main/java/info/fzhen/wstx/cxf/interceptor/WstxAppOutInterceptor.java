package info.fzhen.wstx.cxf.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.interceptors.HolderOutInterceptor;
import org.apache.cxf.phase.Phase;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

/**
 * WS-C/Tx interceptor to add CoordinationContext. This interceptor should be 
 * added to transaction initiator when it send application message.
 * @author fangzhen
 *
 */
public class WstxAppOutInterceptor extends AbstractSoapInterceptor{
	public WstxAppOutInterceptor(){
		super(Phase.PRE_PROTOCOL);
		this.addAfter(HolderOutInterceptor.class.getName());
	}
	public WstxAppOutInterceptor(String phase){
		super(phase);
	}
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> headers  = message.getHeaders();
//		CoordinationContext cc;
//		Header wstxCtxHeader = new Header(null, cc);
//		headers.add(wstxCtxHeader);
	}

}
