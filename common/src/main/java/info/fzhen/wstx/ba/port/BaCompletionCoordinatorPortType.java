
package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ObjectFactory;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


@WebService(name = "BACompletionCoordinatorPortType", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BaCompletionCoordinatorPortType {


    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CommitOperation", action = "http://www.fzhen.info/ws-tx/wsba/Commit")
    @Oneway
    public void commitOperation(
			@WebParam(name = "Commit", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters")
			Notification parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "RollbackOperation", action = "http://www.fzhen.info/ws-tx/wsba/Rollback")
    @Oneway
    public void rollbackOperation(
			@WebParam(name = "Rollback", targetNamespace = "http://www.fzhen.info/ws-tx/wsba", partName = "parameters")
			Notification parameters);

}
