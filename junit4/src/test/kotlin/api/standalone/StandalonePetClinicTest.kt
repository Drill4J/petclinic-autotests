package api.standalone

import com.codeborne.selenide.Selenide.sleep
import io.restassured.RestAssured.given
import junit.framework.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test


class StandalonePetClinicTest {


    companion object {

        lateinit var petclinicUrl: String

        @JvmStatic
        @BeforeClass
        fun junit4Before() {
            petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        }
    }

    @Test
    fun junit4GetOwner4InfoPage() {
        given().get("$petclinicUrl/owners/4").then().statusCode(200)
    }

    @Test
    fun junit4GetOwner4EditPage() {
        given().get("$petclinicUrl/owners/4/edit").then().statusCode(200)
    }


    @Ignore
    @Test
    fun junit4IgnoredTest() {
        given().get("$petclinicUrl/vets")
    }

    @Test
    fun junit4GetHomePage() {
        given().get("$petclinicUrl/")
        sleep(3000)
    }

    @Test
    fun junit4FailedTest() {
        assertTrue(false)
    }
}
