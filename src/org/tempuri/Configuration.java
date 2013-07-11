/**
 * auto-gen
 */

package org.tempuri;

/**
 * Class to configure remote host.
 * 
 */
public final class Configuration {

	/**
	 * Prohibits instantiation.
	 */
	private Configuration() {
	}

	/**
	 * URL.
	 */
	private static String wsUrl = null; // "http://194.228.154.32:60125/ws/Service.svc";

	/**
	 * Configures address of web service, for example
	 * <tt></tt>.
	 * 
	 * @param wsUrl
	 *            The address to access to web service.
	 */
	public static void setConfiguration(final String wsUrl) {
		if (wsUrl != null) {
			Configuration.wsUrl = wsUrl;
		}
	}

	/**
	 * 
	 * @return The address which the web service is deployed.
	 */
	public static String getWsUrl() {
		return wsUrl;
	}
}
