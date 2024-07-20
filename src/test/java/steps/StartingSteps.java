package steps;

import annotations.LazyAutowired;
import config.SetFileProperty;
import configuration.TestContextConfiguration;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import pages.BasePage;
import pages.LoginPage;
import runner.TestContext;
import utils.ScenariosToBeRun;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(classes = {TestContextConfiguration.class})
@CucumberContextConfiguration
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StartingSteps extends BaseSteps {
    private static final Logger logger = LoggerFactory.getLogger(StartingSteps.class);
    private static int scenariosToBeRun;
    private static final AtomicInteger numberOfScenario = new AtomicInteger(0);
    private final ThreadLocal<Integer> scenarios = new ThreadLocal<>();
    private static final String CLASSNAME = StartingSteps.class.getName() + "\t-\t";
    private static String packageName;

    @Autowired
    private Environment env;
    @LazyAutowired
    DriverFactory driverFactory;
    @LazyAutowired
    private BasePage basePage;
    @LazyAutowired
    LoginPage loginPage;

    public StartingSteps(TestContext testContext) {
        super(testContext);
    }

    @Before(order = 0)
    public void setTotalScenarios() {
        logger.info("???????????????????????????????????????????????????????");
        basePage.setEnv(env);
        //Setting number of scenarios to be run
        setScenariosToBeRun();
        logger.info("TOTAL OF SCENARIOS TO BE RUN");
        logger.info(scenariosToBeRun + "");
        //Counter of scenarios executed
        setNumberOfScenarioExecuted();
    }

    @Before(order = 1)
    public void setUp() {
        setConfigProperties(SetFileProperty.getConfigProperties());
        basePage.setConfigProperties(getConfigProperties());
        //logger.info("SCENARIOS ????????????????" + scenarios.get());
        System.out.println("SCENARIOS ???????????????? " + scenarios.get());
        //System.out.println("PROPIEDADEEEEEES ??????????????????????????????????????????? " + getConfigProperties());
        try {
            //Open appium once for 1 thread
            if (scenarios.get() == 1) {
                setDriver(driverFactory.createDriver(getConfigProperties(), env));
                appiumService = DriverFactory.getAppiumService();
                packageName = env.getProperty("environment.citibanamex.appPackage");
            } else {
                getDriver().launchApp();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(CLASSNAME + "Exception while running Set up :" + ex.getMessage());
        }

        logger.info("Session id: " + ((AndroidDriver<?>) getDriver()).getSessionId().toString());
        getTestContext().setPage(loginPage);
        loginPage.isPasswordDisplayed();
    }


    /**
     * Sets total of scenarios to be run
     */
    private void setScenariosToBeRun() {
        scenariosToBeRun = ScenariosToBeRun.totalOfScenarios;
    }

    /**
     * Sets number of scenario executed
     */
    private void setNumberOfScenarioExecuted() {
        numberOfScenario.addAndGet(1);
        scenarios.set(numberOfScenario.get());
        logger.info("NUMBER OF SCENARIO TO BE RUN");
        logger.info(scenarios.get() + "");
    }
}
