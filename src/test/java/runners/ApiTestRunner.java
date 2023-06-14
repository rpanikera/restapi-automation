package runners;


//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        dryRun = false,
        features = "features",
        glue = {"stepDefinition"},
        tags = "@api-post",
        monochrome = true,
        plugin = { "html:target/cucumber-html-report.html", "json:target/cucumber-json-report.json"}
)
public class ApiTestRunner {

    @BeforeClass
    public static void beforeHooks() {
        System.out.println("it is before hooks");
    }

    @AfterClass
    public static void afterHooks() {
        System.out.println("it is after hooks");
    }
}

