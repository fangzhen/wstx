
package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "BACompletionInitiatorPortType", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BaCompletionInitiatorPortType {


    /**
     *
	 * @param parameters
	 */
    @WebMethod(operationName = "CommittedOperation", action = "http://www.fzhen.info/ws-tx/wsba/Committed")
    @Oneway
    public void completedOperation(
			@WebParam(name = "Committed", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters")
			NotificationType parameters);

    /**
     *
	 * @param parameters
	 */
    @WebMethod(operationName = "AbortedOperation", action = "http://www.fzhen.info/ws-tx/wsba/Aborted")
    @Oneway
    public void canceledOperation(
			@WebParam(name = "Aborted", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters")
			NotificationType parameters);

}
