package thewolf.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.FeignClientScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
@FeignClientScan
@EnableCircuitBreaker
public class HelloService {
	
	@Value("${eureka.instance.metadataMap.instanceId}")
	private String instanceId;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EchoClient echoClient;
	
	@Autowired
	private GeolocationService geolocationService;
    
	/**
	 * Call <tt>/rt</tt> before. See http://stackoverflow.com/questions/27483866/spring-cloud-with-feign-ribbon-eureka-unable-to-discover-service-instances
	 * @return
	 */
	@RequestMapping("/hello")
	public String home() {
		String geolocation = geolocationService.getGeolocation();
		return "I call a 'hello' from " + geolocation + " (" + instanceId + ") and receive a " + echoClient.echo();
	}

	@RequestMapping("/rt")
	public String helloRestTemplate() {
		String geolocation = geolocationService.getGeolocation();
		return "I call a 'hello' from " + geolocation + " (" + instanceId + ") and receive a "
				+ restTemplate.getForObject("http://Echo/echo", String.class);
	}

}
