package steps;

import annotations.LazyAutowired;
import config.ConfigProperties;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pages.BasePage;
import pages.LoginPage;
import runner.TestContext;

/**
 * Class to manage initial set up
 */
public class BaseSteps {
    protected static ThreadLocal<TestContext> testContext = new ThreadLocal<>();
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ConfigProperties> configProperties = new ThreadLocal<>();
    protected AppiumDriverLocalService appiumService;

    @Autowired
    private Environment env;
    @LazyAutowired
    private BasePage basePage;
    @LazyAutowired
    DriverFactory driverFactory;
    @LazyAutowired
    LoginPage loginPage;

    public BaseSteps(TestContext testContext) {
        this.testContext.set(testContext);
    }

    public TestContext getTestContext() {
        return this.testContext.get();
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        this.driver.set(driver);
    }

    public ConfigProperties getConfigProperties() {
        return configProperties.get();
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties.set(configProperties);
    }

}
