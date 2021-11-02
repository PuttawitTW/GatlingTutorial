import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation{

    // 1.Http Configuration
    val httpConf = http.baseUrl("http://localhost:8080/swagger-ui/index.html#!/Video_Games/getVideoGame")
        .header("Accept", "application/json")


    // 2. Scenario Definition
    val scn = scenario("My Fisrt Test")
        .exec(http("My first test")
            .get("videogames"))

    // 3. Load Scenario
    setUp(
        scn.inject(atOnceUsers(1))
    ).protocols(httpConf)
    
}