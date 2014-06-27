
package org.oasis_open.docs.ws_tx.wsba._2006._06;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BusinessAgreementWithCoordinatorCompletionParticipantPortType", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BusinessAgreementWithCoordinatorCompletionParticipantPortType {


    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CompleteOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/CompleteOperation")
    @Oneway
    public void completeOperation(
        @WebParam(name = "Complete", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CloseOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/CloseOperation")
    @Oneway
    public void closeOperation(
        @WebParam(name = "Close", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CancelOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/CancelOperation")
    @Oneway
    public void cancelOperation(
        @WebParam(name = "Cancel", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "CompensateOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/CompensateOperation")
    @Oneway
    public void compensateOperation(
        @WebParam(name = "Compensate", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "FailedOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/FailedOperation")
    @Oneway
    public void failedOperation(
        @WebParam(name = "Failed", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "ExitedOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/ExitedOperation")
    @Oneway
    public void exitedOperation(
        @WebParam(name = "Exited", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "NotCompleted", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/NotCompleted")
    @Oneway
    public void notCompleted(
        @WebParam(name = "NotCompleted", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "GetStatusOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/GetStatusOperation")
    @Oneway
    public void getStatusOperation(
        @WebParam(name = "GetStatus", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        NotificationType parameters);

    /**
     * 
     * @param parameters
     */
    @WebMethod(operationName = "StatusOperation", action = "http://docs.oasis-open.org/ws-tx/wsba/2006/06/StatusOperation")
    @Oneway
    public void statusOperation(
        @WebParam(name = "Status", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsba/2006/06", partName = "parameters")
        StatusType parameters);

}