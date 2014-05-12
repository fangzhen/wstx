package info.fzhen.wstx.cxf.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

/**
 * WS-C/Tx out interceptor for coordinationContext soap header
 * @author fangzhen
 *
 */
public class SoapTxOutInterceptor extends AbstractSoapInterceptor{
	public SoapTxOutInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	public SoapTxOutInterceptor(String phase){
		super(phase);
	}
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// TODO Auto-generated method stub
		
	}

}
