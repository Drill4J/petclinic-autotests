package ui.microservices

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class McrChromeUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
        Configuration.browser = "chrome"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @DisplayName("Params test on Chrome browser")
    @ParameterizedTest
    @CsvSource(
        "/#!/welcome, Welcome to Petclinic",
        "/#!/owners, Owners",
        "/#!/owners/10/pets/13/visits, Visits",
        "/#!/vets, Veterinarians"
    )
    fun junit5CheckPageHeader(featureUrl: String, expectedHeader: String) {
        open("$petclinicUrl$featureUrl")
        `$x`("//div[@class='ng-scope']").shouldHave(text(expectedHeader))
    }
}
