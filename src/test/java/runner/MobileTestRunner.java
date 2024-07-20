package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utils.ScenariosToBeRun;

@CucumberOptions(features = "src/test/resources/features/",
        glue = {"steps", "configuration"},
        plugin = {
                "pretty",
                "html:target/cucumber/report.html"
        })
public class MobileTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Check if there are some tags on the command line
     */
    @BeforeClass(alwaysRun = true)
    public void checkTags() {
        String tags = System.getProperty("cucumber.filter.tags");
        if (tags == null) {
            throw new RuntimeException("No se han proporcionado tags para la ejecución de las pruebas.");
        }
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        ScenariosToBeRun.totalOfScenarios = super.scenarios().length;
        return super.scenarios();
    }
}
