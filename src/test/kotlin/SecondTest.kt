import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SecondTest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun before() {
        val propUrl = System.getProperty("petclinic.url", "localhost:8082")
        petclinicUrl = "http://$propUrl"
    }

    @Test
    fun getVetsPage() {
        given().get("$petclinicUrl/vets").then().statusCode(200)
    }

    @Test
    fun getHomePage() {
        given().get("$petclinicUrl/")
    }

    @Test
    fun getErrorPage() {
        given().get("$petclinicUrl/oups")
    }
}
