package ui.standalone

import com.codeborne.selenide.*
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StandaloneChromeUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
        Configuration.browser = "chrome"
        val isRemote = System.getProperty("isRemote", "true").toBoolean()
        if (isRemote) Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
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
        "veterinarians,Veterinarians",
        "trigger a RuntimeException to see how it is handled,Something happened..."
    )
    fun junit5CheckPageHeaderJunit5(tabTitle: String, expectedHeader: String) {
        `$x`("//a[@title='$tabTitle']").click()
        `$x`("//h2").shouldHave(text(expectedHeader))
    }

    @Test
    fun junitClickHeaderTest() {
        `$x`("//a[@title='not existing']").shouldNot(Condition.exist)
    }

    @Test
    fun junitCreatePetTest() {
        val types = listOf("bird", "cat", "dog", "hamster", "lizard", "snake")
        `$x`("//a[@title='find owners']").click()
        `$x`("//button[normalize-space()='Find Owner']").click()
        `$x`("//a[@href='/owners/3']").click()
        `$x`("//a[@href='3/pets/new']").click()
        `$x`("//input[@name='name']").sendKeys("${UUID.randomUUID().hashCode()}")
        `$x`("//input[@name='birthDate']").sendKeys("2020-06-06")
        `$x`("//select[@name='type']").sendKeys(types.random())
        `$x`("//button[normalize-space()='Add Pet']").click()
    }
}