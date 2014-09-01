package info.fzhen.wstx.cxf.interceptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class WstxTransform {
	public static boolean isSupport(String headerNsp) {
		return Wstx200606.isSupport(headerNsp);
	}
	public static JAXBContext getJAXBContext(String headerNsp) throws JAXBException {
		if (Wstx200606.isSupport(headerNsp)){
			return Wstx200606.getJAXBContext(headerNsp);			
		}
		return null;
	}
	
	public static class Wstx200606{
		public static final String WSCOOR_NAMESPACE_URI =
				"http://docs.oasis-open.org/ws-tx/wscoor/2006/06";
		public static final String WSAT_NAMESAPCE_URI = 
				"http://docs.oasis-open.org/ws-tx/wsat/2006/06";
		public static final String WSBA_NAMESPACE_URI = 
				"http://docs.oasis-open.org/ws-tx/wsba/2006/06";
        public static final String WSTX_PRIVATE_URI =
                "http://www.fzhen.info/wstx";

		private static JAXBContext jaxbCtx;;
		public static boolean isSupport(String headerNsp) {
			return headerNsp.equals(WSCOOR_NAMESPACE_URI) ||
					headerNsp.equals(WSAT_NAMESAPCE_URI) ||
					headerNsp.equals(WSBA_NAMESPACE_URI) ||
                    headerNsp.equals(WSTX_PRIVATE_URI);
		}

		public static JAXBContext getJAXBContext(String headerNsp) throws JAXBException {
			synchronized(Wstx200606.class){
				if (jaxbCtx == null){
					jaxbCtx = JAXBContext.newInstance(
							org.oasis_open.docs.ws_tx.wsat._2006._06.ObjectFactory.class,
							org.oasis_open.docs.ws_tx.wsba._2006._06.ObjectFactory.class,
							org.oasis_open.docs.ws_tx.wscoor._2006._06.ObjectFactory.class,
                            info.fzhen.wstx.coordinator.PrivateIdType.class
                    );
				}
			}
			return jaxbCtx;
		}
	}

}
