package info.fzhen.wstx.simpleProcess;

import org.junit.Test;

public class SimpleProcessTest {
	
	@Test
	public void executeProcess(){
		try{
		HelloProcess process = new HelloProcess();
		process.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
