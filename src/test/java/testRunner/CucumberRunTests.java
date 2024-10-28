package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
            features = {"src/test/resources/features"},
            glue = {"stepDefs", "utilities"},
//            tags = "@fixMe",
            monochrome = true,
            plugin = {"pretty","html:target/cucumber-pretty.html", "json:target/cucumber.json"

            }
    )

    public class CucumberRunTests extends AbstractTestNGCucumberTests {

    }

