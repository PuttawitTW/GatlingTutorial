package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Random

class ComplexFeeder extends Simulation {

    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")
        .proxy(Proxy("localhost", 8000))

    val idNumbers = (11 to 20).iterator
    val categories = Seq("Action", "Adventure", "Puzzle", "Chilling")
    val ratings = Seq("Universal", "PG-13", "Mature", "For kids")
    val rnd = new Random()
    val now = LocalDate.now()
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    
    def randomDate(date: LocalDate, rnd: Random) = {
        date.minusDays(rnd.nextInt(30)).format(pattern)
    }

    val customFeeder = Iterator.continually(Map(
        "gameId" -> idNumbers.next(),
        "gameName" -> ("Game No." + rnd.nextInt(1000)),
        "releaseDate" -> randomDate(now, rnd),
        "Score" -> rnd.nextInt(100),
        "Category" -> categories(rnd.nextInt(categories.length)),
        "Rating" -> ratings(rnd.nextInt(ratings.length))
    ))

    def postNewGame() = {
        repeat(5){
            feed(customFeeder)
            .exec(http("POST New Game")
            .post("videogames/")
            .body(ElFileBody("bodies/newGameTemplate.json")).asJson
            .check(status.is(200)))
            .pause(1)
        }
    }

    val scn = scenario("POST New Game with Custom Feeder")
        .exec(postNewGame())

    setUp(
        scn.inject(atOnceUsers(1))
    ).protocols(httpConf)
  
}
