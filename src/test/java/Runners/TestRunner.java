package Runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
        features = "/Users/inaco/Downloads/CucumberTestNG/src/test/resources",
        glue = {"StepDefinitions"},
        tags = "@createEditDeleteChallenge"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
