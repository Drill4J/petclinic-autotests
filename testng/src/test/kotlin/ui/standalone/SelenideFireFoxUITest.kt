package ui.standalone

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide

import org.openqa.selenium.By
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class SelenideFireFoxUITest {

    lateinit var petclinicUrl: String

    @BeforeClass
    fun testNgBeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        Configuration.browser = "firefox"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @BeforeClass
    fun testNgBefore() {
        Selenide.open(petclinicUrl)
    }

    @Test
    fun testNgFindOwnersTestSelenide() {
        Selenide.`$x`("//a[@title='find owners']").click()
        Selenide.`$`(By.id("lastName")).sendKeys("Davis")
        Selenide.`$x`("//button[@type='submit']").click()
        Selenide.`$x`("//a[@href='/owners/2']").shouldHave(Condition.text("Betty Davis"))
    }

    @Test
    fun testNgSimpleTestOnFireFoxWithoutDisplayName() {
        Selenide.`$x`("//a[@title='veterinarians']").click()
        Selenide.`$x`("//td").shouldHave(Condition.text("James Carter"))
    }

}