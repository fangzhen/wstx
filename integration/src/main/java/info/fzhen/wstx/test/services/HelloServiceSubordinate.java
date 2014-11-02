package info.fzhen.wstx.test.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

/**
 * Demo service that uses subordinate
 */
@WebService(serviceName = "helloServiceSubordinate")
public class HelloServiceSubordinate implements HelloService{
	private static final Log __LOG = LogFactory.getLog(HelloServiceSubordinate.class);

	@Resource
	private WebServiceContext wsContext;

	@Override
	public String sayHello() {
		//TODO impl it
		return "Hello";
	}
}
