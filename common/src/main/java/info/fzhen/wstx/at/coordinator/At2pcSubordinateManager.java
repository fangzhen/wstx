package info.fzhen.wstx.at.coordinator;

import info.fzhen.wstx.WstxRtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class At2pcSubordinateManager extends AtAbstractCoorManager<At2pcCoor> {
	private static final Log __LOG = LogFactory.getLog(At2pcSubordinateManager.class);

	private static At2pcSubordinateManager instance;

		public static At2pcSubordinateManager getInstance() {
		if (instance == null) {
			String msg = "2PC protocol service subordiante manager " + At2pcSubordinateManager.class.getSimpleName() +
						"hasn't been initialized. It should be initiated " +
						"by Spring as singleton. Please check your conf file.";
			if (__LOG.isErrorEnabled()) {
				__LOG.error(msg);
			}
			throw new WstxRtException(msg);
		}
		return instance;
	}

	public static void setInstance(At2pcSubordinateManager instance) {
		At2pcSubordinateManager.instance = instance;
	}

}
