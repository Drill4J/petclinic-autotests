package ui.standalone

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StandaloneChromeUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
        Configuration.browser = "chrome"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @BeforeEach
    fun junit5Before() {
        open(petclinicUrl)
    }

    @DisplayName("Params test on Chrome browser")
    @ParameterizedTest
    @CsvSource(
        "home page,Welcome",
        "find owners,Find Owners",
        "veterinarians,Veterinarians"
    )
    fun junit5CheckPageHeaderJunit5(tabTitle: String, expectedHeader: String) {
        `$x`("//a[@title='$tabTitle']").click()
        `$x`("//h2").shouldHave(text(expectedHeader))
    }
}