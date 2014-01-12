
package org.oasis_open.docs.ws_tx.wsat._2006._06;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Coordinator2PCService", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsat/2006/06", wsdlLocation = "file:/home/fangzhen/documents/develop/ws/wkss/wstx/wstx/specs/wsat.wsdl")
public class Coordinator2PCService
    extends Service
{

    private final static URL COORDINATOR2PCSERVICE_WSDL_LOCATION;
    private final static WebServiceException COORDINATOR2PCSERVICE_EXCEPTION;
    private final static QName COORDINATOR2PCSERVICE_QNAME = new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "Coordinator2PCService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/fangzhen/documents/develop/ws/wkss/wstx/wstx/specs/wsat.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        COORDINATOR2PCSERVICE_WSDL_LOCATION = url;
        COORDINATOR2PCSERVICE_EXCEPTION = e;
    }

    public Coordinator2PCService() {
        super(__getWsdlLocation(), COORDINATOR2PCSERVICE_QNAME);
    }

    public Coordinator2PCService(WebServiceFeature... features) {
        super(__getWsdlLocation(), COORDINATOR2PCSERVICE_QNAME, features);
    }

    public Coordinator2PCService(URL wsdlLocation) {
        super(wsdlLocation, COORDINATOR2PCSERVICE_QNAME);
    }

    public Coordinator2PCService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, COORDINATOR2PCSERVICE_QNAME, features);
    }

    public Coordinator2PCService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Coordinator2PCService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CoordinatorPortType
     */
    @WebEndpoint(name = "Coordinator2PC")
    public CoordinatorPortType getCoordinator2PC() {
        return super.getPort(new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "Coordinator2PC"), CoordinatorPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CoordinatorPortType
     */
    @WebEndpoint(name = "Coordinator2PC")
    public CoordinatorPortType getCoordinator2PC(WebServiceFeature... features) {
        return super.getPort(new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "Coordinator2PC"), CoordinatorPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (COORDINATOR2PCSERVICE_EXCEPTION!= null) {
            throw COORDINATOR2PCSERVICE_EXCEPTION;
        }
        return COORDINATOR2PCSERVICE_WSDL_LOCATION;
    }

}
