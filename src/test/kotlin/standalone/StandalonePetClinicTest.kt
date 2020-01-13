package standalone

import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StandalonePetClinicTest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun before() {
        val propUrl = System.getProperty("petclinic.url", "localhost:8082")
        petclinicUrl = "http://$propUrl"
    }

    @Test
    fun getOwner4InfoPage() {
        given().get("$petclinicUrl/owners/4").then().statusCode(200)
    }

    @Test
    fun getOwner4EditPage() {
        given().get("$petclinicUrl/owners/4/edit").then().statusCode(200)
    }


    @Test
    fun getVetsPage() {
        given().get("$petclinicUrl/vets")
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
