package info.fzhen.wstx.test.simpleProcess;

import info.fzhen.wstx.at.participant.WsTransaction;
import info.fzhen.wstx.cxf.interceptor.WstxTransform;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.oasis_open.docs.ws_tx.wscoor._2006._06.CoordinationContext;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.util.ArrayList;
import java.util.List;

@WebService
public abstract class TransactionalProcess implements Process{
	private static final Log __LOG = LogFactory.getLog(TransactionalProcess.class);

	/**
	 * Add coordination context as header when sending application messages.
	 * @param service
	 * @param transaction
	 */
	protected void addTransactionInfo2Client(BindingProvider service, WsTransaction transaction) {
		List<Header> headers;
		headers = (List<Header>) service.getRequestContext().get(Header.HEADER_LIST);
		if (headers == null){
			headers = new ArrayList<Header>();
			service.getRequestContext().put(Header.HEADER_LIST, headers);
		}
		CoordinationContext txCtx = transaction.getCoordinationContext();
		try {
			SoapHeader ctxHeader = new SoapHeader(new QName(WstxTransform.Wstx200606.WSCOOR_NAMESPACE_URI, 
					CoordinationContext.class.getSimpleName()), txCtx, new JAXBDataBinding(CoordinationContext.class));
			headers.add(ctxHeader);
		} catch (JAXBException e) {
			if (__LOG.isWarnEnabled()){
				__LOG.warn("Failed when trying to add soap header to application message");
			}
			if (__LOG.isDebugEnabled()){
				__LOG.debug(e);
			}
			e.printStackTrace();
		}
	}
}
