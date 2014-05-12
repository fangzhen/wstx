package info.fzhen.wstx.simpleProcess;

import org.junit.Test;

public class SimpleProcessTest {
	
	@Test
	public void executeProcess(){
		HelloProcess process = new HelloProcess();
		process.execute();
	}
}
