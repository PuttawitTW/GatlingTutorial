package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Feeder extends Simulation{
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")
        // .proxy(Proxy("localhost", 8000))

    // val csvFeeder = csv("data/games.csv").circular

    val idNumbers = (1 to 10).iterator

    val customFeeder = Iterator.continually(Map("gameId" -> idNumbers.next()))

    def getSpecificGame() = {
        repeat(10){
            feed(customFeeder) // or csvFeeder
            .exec(http("Get a specific video game")
            .get("videogames/${gameId}")
            // .check(jsonPath("$.name").is("${gameName}"))
            .check(status.is(200))) 
            .pause(1)
        }
    }

    val scn = scenario("CSV Feeder")
        .exec(getSpecificGame())

    setUp(
        scn.inject(atOnceUsers(1))
    ).protocols(httpConf)
}