package ui.microservices

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class McrChromeUITest(private var featureUrl: String, private var expectedHeader: String) {

    companion object {
        var petclinicUrl: String = System.getProperty("petclinicUrl", "http://localhost:8080")

        @JvmStatic
        @BeforeClass
        fun junit4BeforeAll() {
            Configuration.browser = "chrome"
            Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
            Configuration.browserCapabilities.setCapability("enableVNC", true)
        }

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("$petclinicUrl/#!/welcome", "Welcome to Petclinic"),
                arrayOf("$petclinicUrl/#!/owners", "Owners"),
                arrayOf("$petclinicUrl/#!/owners/10/pets/13/visits", "Visits"),
                arrayOf("$petclinicUrl/#!/vets", "Veterinarians")
            )
        }
    }

    @Test
    fun junit4CheckPageHeader() {
        open(featureUrl)
        `$x`("//div[@class='ng-scope']").shouldHave(text(expectedHeader))
    }
}
