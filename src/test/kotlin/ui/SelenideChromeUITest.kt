package ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SelenideChromeUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun beforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @BeforeEach
    fun before() {
        Selenide.open(petclinicUrl)
    }

    @DisplayName("Params test on Chrome browser")
    @ParameterizedTest
    @CsvSource(
        "home page,Welcome",
        "find owners,Find Owners",
        "veterinarians,Veterinarians",
        "trigger a RuntimeException to see how it is handled,Something happened..."
    )
    fun checkPageHeaderJunit5(tabTitle: String, expectedHeader: String) {
        Selenide.`$x`("//a[@title='$tabTitle']").click()
        Selenide.`$x`("//h2").shouldHave(Condition.text(expectedHeader))
    }
}