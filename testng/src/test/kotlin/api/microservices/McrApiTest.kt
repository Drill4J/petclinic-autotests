package api.microservices

import io.restassured.RestAssured.given
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.lang.Thread.sleep
import java.net.ConnectException


class McrApiTest {

    lateinit var petclinicUrl: String

    @BeforeClass
    fun testNgBefore() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
        val urls = arrayListOf(
            "$petclinicUrl/api/visit/owners/2/pets/2/visits",
            "$petclinicUrl/api/customer/owners",
            "$petclinicUrl/api/vet/vets"
        )
        var isMcrPetReadyForTests = false
        while (!isMcrPetReadyForTests) {
            urls.forEach {
                isMcrPetReadyForTests = try {
                    given().`when`().get(it).then().extract().response().statusCode == 200
                } catch (e: ConnectException) {
                    false
                }
                sleep(3000)
                print("waiting for services...")
            }
        }
    }

    @Test
    fun testNgCreateNewVisitTest() {
        given()
            .contentType("application/json")
            .body("{\"date\":\"2024-01-01\",\"description\":\"autotest visit\"}")
            .`when`()
            .post("$petclinicUrl/api/visit/owners/2/pets/2/visits")
    }

    @Test
    fun testNgUpdateOwnerInfoTest() {
        given()
            .contentType("application/json")
            .body("{\"id\":1,\"firstName\":\"George\",\"lastName\":\"Franklin\",\"address\":\"110 W. Liberty St.\",\"city\":\"Autotest\",\"telephone\":\"6085551023\",\"pets\":[{\"id\":1,\"name\":\"Leo\",\"birthDate\":\"2010-09-07\",\"type\":{\"id\":1,\"name\":\"cat\"}}]}")
            .`when`()
            .put("$petclinicUrl/api/customer/owners/1")
            .then()
            .statusCode(200)
    }

    @Test
    fun testNgGetVetsInfoTest() {
        given()
            .get("$petclinicUrl/api/vet/vets")
            .then()
            .statusCode(200)
    }
}