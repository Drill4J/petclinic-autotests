package ui.microservices

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Ignore
import org.testng.annotations.Test

class McrFireFoxUITest {

    lateinit var petclinicUrl: String

    @BeforeClass
    fun testNgBeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
        Configuration.browser = "firefox"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @Test
    fun testNgFindOwnersTestSelenide() {
        open(petclinicUrl)
        `$x`("//a[text()='Owners']").click()
        `$x`("//a[@href='#!/owners']").click()
        `$x`("//input[@placeholder='Search Filter']").sendKeys("Davis")
        `$x`("//input[@placeholder='Search Filter']").submit()
        `$x`("//a[@href='#!/owners/details/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Ignore
    @Test
    fun testNgUiIgnoredTest() {
        open(petclinicUrl)
        `$x`("//a[@title='veterinarians']").click()
        `$x`("//td").shouldHave(Condition.text("James Carter"))
    }

    @Test
    fun testNgUiIFailedTest() {
        assertTrue(false)
    }

}
