package ui.standalone

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test


class SelenideChromeUITest() {

    lateinit var petclinicUrl: String


    @BeforeClass
    fun testNgBeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @BeforeMethod
    fun testNgBefore() {
        open(petclinicUrl)
    }

    @DataProvider
    fun getData(): Array<Array<Any?>>? {
        val data = Array(4) {
            arrayOfNulls<Any>(
                2
            )
        }
        data[0][0] = "home page"
        data[0][1] = "Welcome"
        data[1][0] = "find owners"
        data[1][1] = "Find Owners"
        data[2][0] = "veterinarians"
        data[2][1] = "Veterinarians"
        data[3][0] = "trigger a RuntimeException to see how it is handled"
        data[3][1] = "Something happened..."
        return data
    }

    @Test(dataProvider = "getData")
    fun testNgCheckPageHeader(tabTitle: String, expectedHeader: String) {
        `$x`("//a[@title='$tabTitle']").click()
        `$x`("//h2").shouldHave(text(expectedHeader))
    }
}