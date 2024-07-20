package driver;

import config.ConfigProperties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static enums.AppiumEnum.*;
import static enums.AppiumEnum.APPIUM_CLEAR_DEVICE_LOGS_ON_START;

@Component
@ConditionalOnProperty(name = "platformName", havingValue = "Android")
public class DriverCapabilityAndroid implements DriverCapability {

    /**
     * @param configProperties ConfigProperties
     * @param env              Environment
     * @return DesiredCapabilities
     */
    @Override
    public DesiredCapabilities createDiverCapabilities(ConfigProperties configProperties, Environment env) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(APPIUM_PLATFORM_NAME.getAppiumCapability(), configProperties.getPlatformName());
        desiredCapabilities.setCapability(APPIUM_PLATFORM_VERSION.getAppiumCapability(), configProperties.getPlatformVersion());
        desiredCapabilities.setCapability(APPIUM_DEVICE_NAME.getAppiumCapability(), configProperties.getDeviceName());
        desiredCapabilities.setCapability(APPIUM_AUTOMATION_NAME.getAppiumCapability(), configProperties.getAutomationName());
        desiredCapabilities.setCapability(APPIUM_APP_PACKAGE.getAppiumCapability(), env.getProperty("environment.citibanamex.appPackage"));
        desiredCapabilities.setCapability(APPIUM_APP_ACTIVITY.getAppiumCapability(), env.getProperty("environment.citibanamex.appActivity"));
        desiredCapabilities.setCapability(APPIUM_AUTO_GRANT_PERMISSIONS.getAppiumCapability(), Boolean.TRUE);
        desiredCapabilities.setCapability(APPIUM_CLEAR_DEVICE_LOGS_ON_START.getAppiumCapability(), true);
        return desiredCapabilities;
    }
}
