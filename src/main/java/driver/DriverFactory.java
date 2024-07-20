package driver;

import annotations.LazyAutowired;
import annotations.LazyConfiguration;
import config.ConfigProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

@LazyConfiguration
public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<AppiumDriverLocalService> serviceManager = new ThreadLocal<>(); //check
    private static final ThreadLocal<AppiumDriver<WebElement>> driverManager = new ThreadLocal<>(); //check
    private static AppiumDriverLocalService service;
    private static AppiumDriver<WebElement> driver;

    @LazyAutowired
    public DriverBuilder driverBuilder;

    public AppiumDriver createDriver(ConfigProperties configProperties, Environment env) {
        System.out.println("ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        AppiumServiceBuilder builder = this.driverBuilder.createAppiumServiceBuilder(configProperties);
        service = (AppiumDriverLocalService) builder.build();
        service = builder.build();
        service.start();
        driver = driverBuilder.createDriver(builder, configProperties, env);
        logger.info(String.format("Creating %s driver", configProperties.getPlatformName()));
        serviceManager.set(service);
        driverManager.set(driver);
        return driver;
    }

    public static AppiumDriverLocalService getAppiumService() {
        return serviceManager.get();
    }

}
