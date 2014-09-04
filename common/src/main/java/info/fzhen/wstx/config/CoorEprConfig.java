package info.fzhen.wstx.config;

public interface CoorEprConfig extends EprConfig {

	/**
	 * Returns registration service address
	 *
	 * @return
	 */
	String getRegistrationService();

	/**
	 * Returns activation service address
	 *
	 * @return
	 */
	String getActivationService();

}
