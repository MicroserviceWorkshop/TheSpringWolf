package thewolf.hello;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@EnableCircuitBreaker
public class GeolocationService {

	@HystrixCommand(fallbackMethod = "defaultGeolocation")
	public String getGeolocation() {
		if (Math.random() > 0.5) {
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return "Sursee, Switzerland";
	}

	public String defaultGeolocation() {
		return "unknown";
	}

}
