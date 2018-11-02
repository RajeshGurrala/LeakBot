import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "junit:target/cucumber-results.xml","html:target/pretty","html:target/cucumber", "json:target/cucumber.json", "html:target/cucumber-html-report"},tags = {"@customerDetails"})

public class RunnerTest {
}