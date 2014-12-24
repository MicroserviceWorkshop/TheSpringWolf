package thewolf.hello;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Echo")
public interface EchoClient {

	@RequestMapping(method = RequestMethod.GET, value = "/echo")
	String echo();

}
