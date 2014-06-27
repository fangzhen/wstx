package info.fzhen.wstx.coordinator;

import info.fzhen.wstx.coor.config.JaxWsCoorEprConfig;

/**
 * @deprecated
 * @author fangzhen
 *
 */
public class CoordinatorContext {
	private JaxWsCoorEprConfig eprConfiguration;

	public JaxWsCoorEprConfig getEprConfiguration() {
		return eprConfiguration;
	}

	public void setEprConfiguration(JaxWsCoorEprConfig eprConfiguration) {
		this.eprConfiguration = eprConfiguration;
	}
}
