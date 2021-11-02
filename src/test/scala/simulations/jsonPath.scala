package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class jsonPath extends Simulation{
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")

    val scn = scenario("Video Game DB - 3 calls")
        .exec(http("Get all video games")
        .get("videogames")
        .check(jsonPath("$[1].id").saveAs("gameId")))
        .pause(1)

        .exec(http("Get a video game by ID")
        .get("videogames/${gameId}")
        .check(status.in(200, 210), jsonPath("$.name").is("Gran Turismo 4")))
        .pause(1)


    setUp(
        scn.inject(atOnceUsers(1))
    ).protocols(httpConf)
}