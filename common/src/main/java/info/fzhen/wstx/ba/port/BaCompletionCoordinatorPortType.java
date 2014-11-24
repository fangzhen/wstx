
package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsba._2006._06.NotificationType;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "BACompletionCoordinatorPortType", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BaCompletionCoordinatorPortType {


    /**
     *
	 * @param parameters
	 */
    @WebMethod(operationName = "CommitOperation", action = "http://www.fzhen.info/ws-tx/wsba/Commit")
    @Oneway
    public void completeOperation(
			@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters")
			NotificationType parameters);

    /**
     *
	 * @param parameters
	 */
    @WebMethod(operationName = "RollbackOperation", action = "http://www.fzhen.info/ws-tx/wsba/Rollback")
    @Oneway
    public void cancleOperation(
			@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters")
			NotificationType parameters);

}
