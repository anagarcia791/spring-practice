package driver;

import config.ConfigProperties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static enums.AppiumEnum.*;

@Component
@ConditionalOnProperty(name = "platformName", havingValue = "iOS")
public class DriverCapabilityIOS implements DriverCapability {

    /**
     * Create capabilities for citibanamex app
     *
     * @param configProperties ConfigProperties
     * @param env              Environment
     * @return DesiredCapabilities
     */
    @Override
    public DesiredCapabilities createDiverCapabilities(ConfigProperties configProperties, Environment env) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(APPIUM_PLATFORM_NAME.getAppiumCapability(), configProperties.getPlatformName().toLowerCase());
        desiredCapabilities.setCapability(APPIUM_PLATFORM_VERSION.getAppiumCapability(), configProperties.getPlatformVersion());
        desiredCapabilities.setCapability(APPIUM_DEVICE_NAME.getAppiumCapability(), configProperties.getDeviceName());
        desiredCapabilities.setCapability(APPIUM_AUTOMATION_NAME.getAppiumCapability(), "XCUITest");
        desiredCapabilities.setCapability(APPIUM_WDA_LAUNCH_TIMEOUT.getAppiumCapability(), "240000");
        desiredCapabilities.setCapability(APPIUM_WDA_CONNECTION_TIMEOUT.getAppiumCapability(), "240000");
        desiredCapabilities.setCapability(APPIUM_WDA_STARTUP_RETRY_INTERVAL.getAppiumCapability(), "20000");
        desiredCapabilities.setCapability(APPIUM_AUTO_ACCEPT_ALERTS.getAppiumCapability(), true);
        desiredCapabilities.setCapability(APPIUM_UDID.getAppiumCapability(), configProperties.getUdid());
        return desiredCapabilities;
    }
}
