package microservices

import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MicroServicesPetClinicTest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun before() {
        val propUrl = System.getProperty("petclinic.url","localhost:8082")
        petclinicUrl = "http://$propUrl"
    }

    @Test
    fun createNewVisitTest() {
        given()
            .contentType("application/json")
            .body("{\"date\":\"2020-01-01\",\"description\":\"autotest visit\"}")
            .`when`()
            .post("$petclinicUrl/api/visit/owners/1/pets/1/visits")
            .then()
            .statusCode(204)
    }

    @Test
    fun updateOwnerInfoTest() {
        given()
            .contentType("application/json")
            .body("{\"id\":1,\"firstName\":\"George\",\"lastName\":\"Franklin\",\"address\":\"110 W. Liberty St.\",\"city\":\"Autotest\",\"telephone\":\"6085551023\",\"pets\":[{\"id\":1,\"name\":\"Leo\",\"birthDate\":\"2010-09-07\",\"type\":{\"id\":1,\"name\":\"cat\"}}]}")
            .`when`()
            .put("$petclinicUrl/api/customer/owners/1")
            .then()
            .statusCode(200)
    }

    @Test
    fun getVetsInfoTest() {
        given()
            .get("$petclinicUrl/api/vet/vets")
            .then()
            .statusCode(200)
    }
}