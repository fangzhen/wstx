package info.fzhen.wstx.ba.cc;

import info.fzhen.wstx.AbstractCoordinatorProtocolMgr;
import info.fzhen.wstx.WstxRtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Coordinator completion coordinator side protocol manager
 */
public class CcCoordinatorProtocolMgr extends AbstractCoordinatorProtocolMgr<CcCoordinatorProtocolService> {
	private static final Log __LOG = LogFactory.getLog(CcCoordinatorProtocolMgr.class);

	private static CcCoordinatorProtocolMgr instance;

	public static CcCoordinatorProtocolMgr getInstance() {
		if (instance == null){
			String msg = "Business activity coordinator completion protocol " +
					"service manager(" + CcCoordinatorProtocolMgr.class +
					") singleton instance hasn't been initialized. It should be " +
					"initialized by Spring as singleton. Please check your " +
					"configuration file.";
			if (__LOG.isErrorEnabled()){
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}

	public static void setInstance(CcCoordinatorProtocolMgr instance){
		CcCoordinatorProtocolMgr.instance = instance;
	}

}
