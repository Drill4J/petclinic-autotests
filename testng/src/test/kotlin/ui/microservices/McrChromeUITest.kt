package ui.microservices

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class McrChromeUITest {

    lateinit var petclinicUrl: String

    @BeforeClass
    fun testNgBeforeAll() {
        petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8080")
        Configuration.browser = "chrome"
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
        Configuration.browserCapabilities.setCapability("enableVNC", true)
    }

    @DataProvider
    fun getData(): Array<Array<Any?>> {
        val data = Array(4) {
            arrayOfNulls<Any>(
                2
            )
        }
        data[0][0] = "/#!/welcome"
        data[0][1] = "Welcome to Petclinic"
        data[1][0] = "/#!/owners"
        data[1][1] = "Owners"
        data[2][0] = "/#!/owners/10/pets/13/visits"
        data[2][1] = "Visits"
        data[3][0] = "/#!/vets"
        data[3][1] = "Veterinarians"
        return data
    }

    @Test(dataProvider = "getData")
    fun testNgCheckPageHeader(featureUrl: String, expectedHeader: String) {
        open("$petclinicUrl$featureUrl")
        `$x`("//div[@class='ng-scope']").shouldHave(text(expectedHeader))
    }
}
