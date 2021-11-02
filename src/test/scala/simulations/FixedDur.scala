package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FixedDur extends Simulation{
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

    val scn = scenario("Fixed Time Simulation Video Game DB - 3 calls")
        .forever() {
            exec(getAllGames())
            .pause(5)
    
            .exec(getGameById())
            .pause(5)
    
            .exec(getAllGames())
            .pause(5)
            
        }

    setUp(
        scn.inject(
            nothingFor(5.seconds),
            atOnceUsers(10),
            rampUsers(30) during(30.seconds)
        )
    ).protocols(httpConf.inferHtmlResources())
    .maxDuration(1.minute)
    
}