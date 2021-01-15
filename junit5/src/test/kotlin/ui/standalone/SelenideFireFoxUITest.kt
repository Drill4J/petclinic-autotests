package ui.standalone

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.*
import org.openqa.selenium.By

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SelenideFireFoxUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        Configuration.browser = "firefox"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @BeforeEach
    fun junit5Before() {
        Selenide.open(petclinicUrl)
    }

    @DisplayName("Simple test 1 on FireFox browser")
    @Test
    fun junit5FindOwnersTestSelenide() {
        Selenide.`$x`("//a[@title='find owners']").click()
        Selenide.`$`(By.id("lastName")).sendKeys("Davis")
        Selenide.`$x`("//button[@type='submit']").click()
        Selenide.`$x`("//a[@href='/owners/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Test
    fun junit5SimpleTestOnFireFoxWithoutDisplayName() {
        Selenide.`$x`("//a[@title='veterinarians']").click()
        Selenide.`$x`("//td").shouldHave(Condition.text("James Carter"))
    }

}