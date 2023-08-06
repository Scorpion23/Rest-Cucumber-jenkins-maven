package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		
		glue = {"stepDefinition"},
				monochrome = true,
		tags= "@DeletePlace"
		)

public class TestRunner {

}
