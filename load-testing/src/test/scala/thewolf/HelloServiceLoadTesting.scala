package thewolf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class HelloServiceLoadTesting extends Simulation {

	val scn = scenario("hello service scenario")
	          .exec(http("hello")
	          .put("http://localhost:8080/hello/rt"))
	
	setUp(scn.inject(
			rampUsersPerSec(1) to(10) during(10), 
			constantUsersPerSec(10) during(10)
		  )
	)
}