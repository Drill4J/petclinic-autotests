package ui.standalone

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.*
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.openqa.selenium.By

class StandaloneFireFoxUITest {

    companion object {

        lateinit var petclinicUrl: String

        @JvmStatic
        @BeforeClass
        fun junit4BeforeAll() {
            petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
            Configuration.browser = "firefox"
            Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
            Configuration.browserCapabilities.setCapability("enableVNC", true)
        }

    }

    @Test
    fun junit4FindOwnersTestSelenide() {
        open(petclinicUrl)
        `$x`("//a[@title='find owners']").click()
        `$`(By.id("lastName")).sendKeys("Davis")
        `$x`("//button[@type='submit']").click()
        `$x`("//a[@href='/owners/2']").shouldHave(Condition.text("Betty Davis"))
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
