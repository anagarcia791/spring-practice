package pages;

import annotations.Page;
import config.ConfigProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

@Page
public class BasePage {

    private final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected AppiumDriver driver;
    protected AppiumFieldDecorator appiumFieldDecorator;
    private final Duration TIMEOUT_DECORATOR = ofSeconds(5);
    private static Environment env;
    private static ThreadLocal<ConfigProperties> configProperties = new ThreadLocal<>();

    /**
     * Just for Spring implementation, the driver will be set using a Spring Aspect.
     * If your page is NOT managed by Spring, please use Driver-argument method instead of this.
     */
    public BasePage() {
        initAppiumFieldDecorator();
    }

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        initAppiumFieldDecorator();
    }

    /**
     * Init Appium Field Decorator
     */
    private void initAppiumFieldDecorator() {
        appiumFieldDecorator = new AppiumFieldDecorator(driver, TIMEOUT_DECORATOR);
    }

    /**
     * In any constructor page, to init WebElements use:
     * PageFactory.initElements(getAppiumFieldDecorator(), this);
     *
     * @return appiumFieldDecorator
     */
    protected AppiumFieldDecorator getAppiumFieldDecorator() {
        return appiumFieldDecorator;
    }

    /**
     * This method sets the Environment variable
     *
     * @param env Environment variable
     */
    public void setEnv(Environment env) {
        this.env = env;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties.set(configProperties);
    }

    public ConfigProperties getConfigProperties() {
        return configProperties.get();
    }
}
