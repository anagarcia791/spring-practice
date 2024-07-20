package driver;

import config.ConfigProperties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static enums.AppiumEnum.APPIUM_NEW_COMMAND_TIMEOUT;

@Component
public interface DriverCapability {
    /**
     * Method which creates the desired capabilities for appium service
     *
     * @return Desired capabilities for appium service
     */
    public static DesiredCapabilities createInitCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(APPIUM_NEW_COMMAND_TIMEOUT.getAppiumCapability(), 480);
        return desiredCapabilities;
    }

    DesiredCapabilities createDiverCapabilities(ConfigProperties configProperties, Environment env);
}
