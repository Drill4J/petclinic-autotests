package ui.microservices

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class McrFireFoxUITest {

    lateinit var petclinicUrl: String

    @BeforeAll
    fun junit5BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
        Configuration.browser = "firefox"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @Test
    fun junit5FindOwnersTestSelenide() {
        open(petclinicUrl)
        `$x`("//a[text()='Owners']").click()
        `$x`("//a[@href='#!/owners']").click()
        `$x`("//input[@placeholder='Search Filter']").sendKeys("Davis")
        `$x`("//input[@placeholder='Search Filter']").submit()
        `$x`("//a[@href='#!/owners/details/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Disabled
    @Test
    fun junit5UiIgnoredTest() {
        open(petclinicUrl)
        `$x`("//a[@title='veterinarians']").click()
        `$x`("//td").shouldHave(Condition.text("James Carter"))
    }

    @Test
    fun junit5UiIFailedTest() {
        assertTrue(false)
    }

}
