package info.fzhen.wstx.test.processes;

import info.fzhen.wstx.test.ClientProxies;
import org.testng.annotations.Test;

public class ProcessesIT {
	private ClientProxies proxies = ClientProxies.getInstance();

	private void executeProcess(String name){
		Process p = proxies.getService(name);
		p.execute();
	}
	@Test
	public void executeHelloProcess() throws Exception {
		executeProcess("helloProcess");
	}

	@Test
	public void executeAtProcessSubordinate() throws Exception {
		executeProcess("atProcessSubordinate");
	}

	@Test
	public void executeBaDemoProcess(){
		executeProcess("baDemoProcess");
	}
}
