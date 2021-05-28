package api.microservices

import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class McrApiTest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5Before() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
    }

    @Test
    fun junit5CreateNewVisitTest() {
        given()
            .contentType("application/json")
            .body("{\"date\":\"2024-01-01\",\"description\":\"autotest visit\"}")
            .`when`()
            .post("$petclinicUrl/api/visit/owners/2/pets/2/visits")
            .then()
            .statusCode(201)
    }

    @Test
    fun junit5UpdateOwnerInfoTest() {
        given()
            .contentType("application/json")
            .body("{\"id\":1,\"firstName\":\"George\",\"lastName\":\"Franklin\",\"address\":\"110 W. Liberty St.\",\"city\":\"Autotest\",\"telephone\":\"6085551023\",\"pets\":[{\"id\":1,\"name\":\"Leo\",\"birthDate\":\"2010-09-07\",\"type\":{\"id\":1,\"name\":\"cat\"}}]}")
            .`when`()
            .put("$petclinicUrl/api/customer/owners/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun junit5GetVetsInfoTest() {
        given()
            .get("$petclinicUrl/api/vet/vets")
            .then()
            .statusCode(200)
    }
}