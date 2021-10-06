package ui.standalone

import com.codeborne.selenide.*
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

@RunWith(Parameterized::class)
class StandaloneChromeUITest(private var tabTitle: String, private var expectedHeader: String) {

    companion object {
        lateinit var petclinicUrl: String

        @JvmStatic
        @BeforeClass
        fun junit4BeforeAll() {
            petclinicUrl = System.getProperty("petclinicUrl", "http://localhost:8087")
            val isRemote = System.getProperty("isRemote", "true").toBoolean()
            if (isRemote) Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub"
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

    @Test
    fun junit4ClickHeaderTest(){
        `$x`("//a[@title='not existing']").shouldNot(Condition.exist)
    }

    @Test
    fun junit4CreatePetTest() {
        val types = listOf("bird", "cat", "dog", "hamster", "lizard", "snake")
        `$x`("//a[@title='find owners']").click()
        `$x`("//button[normalize-space()='Find Owner']").click()
        `$x`("//a[@href='/owners/3']").click()
        `$x`("//a[@href='3/pets/new']").click()
        `$x`("//input[@name='name']").sendKeys("${UUID.randomUUID().hashCode()}")
        `$x`("//input[@name='birthDate']").sendKeys("2020-06-06")
        `$x`("//select[@name='type']").sendKeys(types.random())
        `$x`("//button[normalize-space()='Add Pet']").click()
    }
}
