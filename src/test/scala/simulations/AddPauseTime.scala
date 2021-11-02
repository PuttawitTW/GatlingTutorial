package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class AddPauseTime extends Simulation{
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")

    def getAllGames() = {
        exec(
            http("Get all games")
            .get("videogames/")
            .check(status.is(200))
        )
    }

    def getGameById() = {
        exec(
            http("Get a game by Id")
            .get("videogames/1")
            .check(status.is(200))
        )
    }

    val scn = scenario("Video Game DB - 3 calls")
        .exec(getAllGames())
        .pause(2)

        .exec(getGameById())
        .pause(2)

        .exec(getAllGames())
        .pause(2)

    setUp(
        scn.inject(
            nothingFor(5.seconds),
            atOnceUsers(5),
            rampUsers(10) during(10.seconds)
        )
    ).protocols(httpConf.inferHtmlResources())
}