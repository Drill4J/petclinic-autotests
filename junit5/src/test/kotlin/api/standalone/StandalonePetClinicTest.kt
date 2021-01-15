package api.standalone

import com.codeborne.selenide.Selenide.sleep
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StandalonePetClinicTest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5Before() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
    }

    @Test
    fun junit5GetOwner4InfoPage() {
        given().get("$petclinicUrl/owners/4").then().statusCode(200)
    }

    @Test
    fun junit5GetOwner4EditPage() {
        given().get("$petclinicUrl/owners/4/edit").then().statusCode(200)
    }


    @Disabled
    @Test
    fun junit5IgnoredTest() {
        given().get("$petclinicUrl/vets")
    }

    @Test
    fun junit5GetHomePage() {
        given().get("$petclinicUrl/")
        sleep(3000)
    }

    @Test
    fun junit5FailedTest() {
        assertTrue(false)
    }
}
