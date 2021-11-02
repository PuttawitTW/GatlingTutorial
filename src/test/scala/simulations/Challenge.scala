package simulations
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import scala.concurrent.duration._
import scala.util.Random
import java.time.format.DateTimeFormatter
import java.time.LocalDate


class Challenge extends Simulation {
  
    val httpConf = http.baseUrl("http://localhost:8080/app/")
        .header("Accept", "application/json")
        .proxy(Proxy("localhost", 8000))


    def getParam(propertyName: String, defaultValue: String) = {
        Option(System.getenv(propertyName))
            .orElse(Option(System.getProperty(propertyName)))
            .getOrElse(defaultValue)
    }

    def usercount: Int = getParam("USERS", "10").toInt
    def ramp_user: Int = getParam("RAMPUSER", "30").toInt
    def ramp_dur: Int = getParam("RAMPDUR", "10").toInt
    def duration: Int = getParam("DUR", "60").toInt

    before{
        println(s"Users: ${usercount}")
        println(s"Ramp users: ${ramp_user}")
        println(s"Ramp duration: ${ramp_dur}")
        println(s"Duration: ${duration}")
    }

    def getAllGames() = {
        exec(http("Get all games")
        .get("videogames/")
        .check(status.is(200))
        ).pause(2)
    }

    def getById() = {
        exec(http("Get one game by Id")
        .get("videogames/${gameId}")
        .check(jsonPath("$.id").is("${gameId}"))
        .check(jsonPath("$.name").is("${gameName}"))
        .check(status.is(200))
        ).pause(2)
    }

    def addGame() = {
        feed(customFeeder)
        .exec(http("Add a new game ")
        .post("videogames/")
        .body(ElFileBody("bodies/newGameTemplate.json")).asJson
        .check(status.is(200))
        ).pause(2)
    }



    def deleteGame() = {
        exec(http("Delete a game")
        .delete("videogames/${gameId}")
        .check(status.is(200))
        ).pause(2)
    }

    val idNumbers = (11 to 999).iterator
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

    val scn = scenario("Full Journey Script")
    .forever() {
        exec(getAllGames())
        .exec(addGame())
        .exec(getById())
        .exec(deleteGame())
        .exec(getAllGames())

    }

    setUp(
        scn.inject(
            nothingFor(3),
            atOnceUsers(usercount),
            rampUsers(ramp_user) during(ramp_dur)
        )
    ).protocols(httpConf.inferHtmlResources())
    .maxDuration(duration)
    .assertions(
        global.responseTime.max.lt(500),
        global.successfulRequests.percent.gt(99)
    )

}
