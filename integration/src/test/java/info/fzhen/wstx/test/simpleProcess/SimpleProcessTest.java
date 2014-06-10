package info.fzhen.wstx.test.simpleProcess;

import info.fzhen.wstx.test.simpleProcess.HelloProcess;

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
