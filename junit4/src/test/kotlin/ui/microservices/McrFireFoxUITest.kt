package ui.microservices

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test

class McrFireFoxUITest {


    companion object {

        lateinit var petclinicUrl: String

        @JvmStatic
        @BeforeClass
        fun junit4BeforeAll() {
            petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
            Configuration.browser = "firefox"
            Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
            Configuration.browserCapabilities.setCapability("enableVNC", true)
        }

    }

    @Test
    fun junit4FindOwnersTestSelenide() {
        open(petclinicUrl)
        `$x`("//a[text()='Owners']").click()
        `$x`("//a[@href='#!/owners']").click()
        `$x`("//input[@placeholder='Search Filter']").sendKeys("Davis")
        `$x`("//input[@placeholder='Search Filter']").submit()
        `$x`("//a[@href='#!/owners/details/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Ignore
    @Test
    fun junit4UiIgnoredTest() {
        open(petclinicUrl)
        `$x`("//a[@title='veterinarians']").click()
        `$x`("//td").shouldHave(Condition.text("James Carter"))
    }

    @Test
    fun junit4UiIFailedTest() {
        assertTrue(false)
    }

}
