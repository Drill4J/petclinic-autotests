package ui.standalone

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SelenideChromeUITest(private var tabTitle: String, private var expectedHeader: String) {

    companion object{
        lateinit var petclinicUrl: String

        @JvmStatic
        @BeforeClass
        fun junit4BeforeAll() {
            petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8082")
            Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
            Configuration.browserCapabilities.setCapability("enableVNC", true)
        }

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("home page", "Welcome"),
                arrayOf("find owners", "Find Owners"),
                arrayOf("veterinarians", "Veterinarians"),
                arrayOf("trigger a RuntimeException to see how it is handled", "Something happened...")
            )
        }
    }


    @Before
    fun junit4Before() {
        open(petclinicUrl)
    }

    @Test
    fun junit4CheckPageHeader() {
        `$x`("//a[@title='$tabTitle']").click()
        `$x`("//h2").shouldHave(text(expectedHeader))
    }
}