package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.util.EPRConfiguration;

/**
 * @deprecated
 * @author fangzhen
 *
 */
public class CoordinatorContext {
	private EPRConfiguration eprConfiguration;

	public EPRConfiguration getEprConfiguration() {
		return eprConfiguration;
	}

	public void setEprConfiguration(EPRConfiguration eprConfiguration) {
		this.eprConfiguration = eprConfiguration;
	}
}
