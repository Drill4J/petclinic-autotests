package drill;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "cucumber-junit/src/test/resources/com.epam.drill")
public class CucumberRunner {
}
