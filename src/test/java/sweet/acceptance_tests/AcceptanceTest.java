package sweet.acceptance_tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features ="src/use_case",
        plugin = {"html:target/cucumber/wikipedia.html"}
        , monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"sweet.acceptance_tests"}
        )
public class AcceptanceTest {



}
