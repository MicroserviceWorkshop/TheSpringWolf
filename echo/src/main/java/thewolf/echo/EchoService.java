package thewolf.echo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class EchoService {
	
	@Value("${eureka.instance.metadataMap.instanceId}")
	private String instanceId;
    
    @RequestMapping("/echo")
    public String home() {
        return "Echo from " + instanceId;
    }

}
