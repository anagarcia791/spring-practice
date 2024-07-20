package driver;

import config.ConfigProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static enums.AppiumEnum.*;

@Component
public interface DriverBuilder {
    public static final Logger logger = LoggerFactory.getLogger(DriverBuilder.class);

    /**
     * Method which creates the desired capabilities for appium service
     *
     * @return AppiumServiceBuilder
     */
    public default AppiumServiceBuilder createAppiumServiceBuilder(ConfigProperties configProperties) {
        System.out.println("WTF KLJDFHSLJDHFLSAKJHFA");
        AppiumServiceBuilder builder;
        logger.info("Creating appium service using any free port");
        //System.out.println("Creating appium service using any free port");
        builder = new AppiumServiceBuilder().withCapabilities(DriverCapability.createInitCapabilities())
                .withIPAddress(configProperties.getServiceURL())
                .usingAnyFreePort()
                .withArgument(BASE_PATH::getAppiumCapability, BASE_PATH_VALUE.getAppiumCapability())
                .withArgument(LOG_LEVEL::getAppiumCapability, LOG_LEVEL_VALUE.getAppiumCapability());
        System.out.println("BUILDER ??????????????????????????????????????????? " + builder);
        return builder;
    }

    public AppiumDriver<WebElement> createDriver(AppiumServiceBuilder builder, ConfigProperties configProperties, Environment env);
}
