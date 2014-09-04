package info.fzhen.wstx.config;

/**
 * Atomic transaction coordinator side Endpoint reference configuration.
 *
 * @author fangzhen
 */
public interface AtCoorEprConfig extends EprConfig {
	/**
	 * Return completion protocol coordinator address.
	 *
	 * @return
	 */
	public String getAtCompletionCoorAddress();

	/**
	 * Return 2PC protocol coordinator address.
	 *
	 * @return
	 */
	public String getAt2PcCoorAddress();
}
