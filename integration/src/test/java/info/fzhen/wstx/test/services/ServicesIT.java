package info.fzhen.wstx.test.services;

import info.fzhen.wstx.test.ClientProxies;
import org.testng.annotations.Test;

/**
 * Test services used.
 *
 * @author fangzhen
 */
public class ServicesIT {
	private ClientProxies proxies = ClientProxies.getInstance();

	@Test
	public void testHelloService() {
		HelloService port = proxies.getService("helloService");
		assert "Hello".equals(port.sayHello());
	}
}
