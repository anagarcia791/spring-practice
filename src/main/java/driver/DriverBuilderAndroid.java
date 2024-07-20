package driver;

import annotations.LazyAutowired;
import config.ConfigProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "platformName", havingValue = "Android")
public final class DriverBuilderAndroid implements DriverBuilder {

    @LazyAutowired
    DriverCapability capabilityDriver;

    /**
     * Create driver to one thread with service builder
     *
     * @param builder          AppiumServiceBuilder
     * @param configProperties ConfigProperties
     * @param env              Environment
     * @return MobileDriver<MobileElement>
     */
    @Override
    public AppiumDriver<WebElement> createDriver(AppiumServiceBuilder builder, ConfigProperties configProperties, Environment env) {
        return new AndroidDriver<>(builder, capabilityDriver.createDiverCapabilities(configProperties, env));
    }
}
