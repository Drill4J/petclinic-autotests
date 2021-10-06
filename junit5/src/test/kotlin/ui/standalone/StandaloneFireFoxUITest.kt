package ui.standalone

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.By

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StandaloneFireFoxUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
        Configuration.browser = "firefox"
        val isRemote = System.getProperty("isRemote", "true").toBoolean()
        if (isRemote) Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
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

    @Disabled
    @Test
    fun junit5UiIgnoredTest() {
        Selenide.`$x`("//a[@title='veterinarians']").click()
        Selenide.`$x`("//td").shouldHave(Condition.text("James Carter"))
    }

    @Test
    fun junit5UiFailedTest() {
        assertTrue(false)
    }

}