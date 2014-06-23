package info.fzhen.wstx.cxf.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.ContextUtils;

/**
 * WS-C/Tx in interceptor for coordinationContext soap header
 * @author fangzhen
 *
 */
public class SoapTxCoorInInterceptor extends AbstractSoapInterceptor{
	public SoapTxCoorInInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	public SoapTxCoorInInterceptor(String phase){
		super(phase);
	}
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		AddressingProperties maps = ContextUtils.retrieveMAPs(message, false, false);
	}

}
