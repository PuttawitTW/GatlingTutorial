package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class RuntimeParam extends Simulation{
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")


    def getParam(propertyName: String, defaultValue: String) = {
        Option(System.getenv(propertyName))
            .orElse(Option(System.getProperty(propertyName)))
            .getOrElse(defaultValue)
    }

    def USERS: Int = getParam("USERS", "10").toInt
    def RAMP_DUR: Int = getParam("RAMP", "10").toInt
    def DURATION: Int = getParam("DURATION", "60").toInt

    before{
        println(s"Running test with ${USERS} users")
        println(s"Ramping users over ${RAMP_DUR} secs")
        println(s"Running test for ${DURATION} secs")
    }
    
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
            atOnceUsers(USERS),
            rampUsers(30) during(RAMP_DUR)
        )
    ).protocols(httpConf.inferHtmlResources())
    .maxDuration(DURATION)
    
}