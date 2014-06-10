package info.fzhen.wstx.test.services;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements HelloService{

	public String sayHello() {
		String helloStr = "Hello";
		return helloStr;
	}

}
