package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CheckResponse extends Simulation{
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")

    val scn = scenario("Video Game DB - 3 calls")
        .exec(getAllGames())
        .exec(getGameById())

    def getAllGames() = {
        repeat(5){
            exec(http("Get all video games")
            .get("videogames")
            .check(status.is(200)))
        }
    }

    def getGameById() = {
        exec(http("Get a video game by ID")
        .get("videogames/1")
        .check(status.in(200, 210)))
    }

    setUp(
        scn.inject(atOnceUsers(1))
    ).protocols(httpConf)
}