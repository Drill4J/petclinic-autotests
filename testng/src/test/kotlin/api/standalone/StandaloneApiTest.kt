package api.standalone

import com.codeborne.selenide.Selenide.sleep
import io.restassured.RestAssured.given
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Ignore
import org.testng.annotations.Test

class StandaloneApiTest {

    lateinit var petclinicUrl: String


    @BeforeClass
    fun testNgBefore() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
    }

    @Test
    fun testNgGetOwner4InfoPage() {
        given().get("$petclinicUrl/owners/4").then().statusCode(200)
    }

    @Test
    fun testNgGetOwner4EditPage() {
        given().get("$petclinicUrl/owners/4/edit").then().statusCode(200)
    }


    @Ignore
    @Test
    fun testNgIgnoredTest() {
        given().get("$petclinicUrl/vets")
    }

    @Test
    fun testNgGetHomePage() {
        given().get("$petclinicUrl/")
        sleep(3000)
    }

    @Test
    fun testNgFailedTest() {
        assertTrue(false)
    }

    @Test
    fun testNgShouldReturn404Status() {
        given().get("$petclinicUrl/not/existing").then().statusCode(404)
    }
}
