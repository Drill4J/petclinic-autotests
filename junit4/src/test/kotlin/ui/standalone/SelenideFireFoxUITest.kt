package ui.standalone

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

import org.openqa.selenium.By

class SelenideFireFoxUITest {

    lateinit var petclinicUrl: String

    @Rule
    @BeforeClass
    fun junit4BeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        Configuration.browser = "firefox"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @Before
    fun junit4Before() {
        Selenide.open(petclinicUrl)
    }

    @Test
    fun junit4FindOwnersTestSelenide() {
        Selenide.`$x`("//a[@title='find owners']").click()
        Selenide.`$`(By.id("lastName")).sendKeys("Davis")
        Selenide.`$x`("//button[@type='submit']").click()
        Selenide.`$x`("//a[@href='/owners/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Test
    fun junit4SimpleTestOnFireFoxWithoutDisplayName() {
        Selenide.`$x`("//a[@title='veterinarians']").click()
        Selenide.`$x`("//td").shouldHave(Condition.text("James Carter"))
    }

}