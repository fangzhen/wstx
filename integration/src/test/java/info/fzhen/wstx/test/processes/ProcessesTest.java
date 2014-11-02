package info.fzhen.wstx.test.processes;

import info.fzhen.wstx.test.ClientProxies;
import org.testng.annotations.Test;

public class ProcessesTest {
	private ClientProxies proxies = ClientProxies.getInstance();

	private void executeProcess(String name){
		Process p = proxies.getService(name);
		p.execute();
	}
	@Test(groups = {"integration"})
	public void executeHelloProcess() throws Exception {
		executeProcess("helloProcess");
	}
}
