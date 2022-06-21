package in.reqres.runner;


import cucumber.api.CucumberOptions;
import in.reqres.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/java/resources/feature",
        glue = "in/reqres",
        tags = "@Test"
)
public class TestRunner extends TestBase {

}
