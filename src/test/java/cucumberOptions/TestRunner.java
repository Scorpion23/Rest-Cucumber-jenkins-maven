package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",plugin={"json:target/jsonReports/cucumber.json"},
		
		glue = {"stepDefinition"},
				monochrome = true,
		tags= "@all"
		)

public class TestRunner {

}
