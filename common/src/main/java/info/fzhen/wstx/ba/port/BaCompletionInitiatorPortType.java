
package info.fzhen.wstx.ba.port;

import org.oasis_open.docs.ws_tx.wsat._2006._06.Notification;
import org.oasis_open.docs.ws_tx.wsat._2006._06.ObjectFactory;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


@WebService(name = "BACompletionInitiatorPortType", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BaCompletionInitiatorPortType {


    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CommittedOperation", action = "http://www.fzhen.info/ws-tx/wsba/Committed")
    @Oneway
    public void completedOperation(
			@WebParam(name = "Committed", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters")
			Notification parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "AbortedOperation", action = "http://www.fzhen.info/ws-tx/wsba/Aborted")
    @Oneway
    public void canceledOperation(
			@WebParam(name = "Aborted", targetNamespace = "http://www.fzhen.info/ws-tx/wsba/", partName = "parameters")
			Notification parameters);

}
