package config;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Class to manage mobile properties
 */
public class ConfigProperties {

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String automationName;
    private String serviceURL;
    private String udid;

    public ConfigProperties(String property) {
        try {
            String systemPlatformVersion = System.getProperty("platformVersion");
            String systemDeviceName = System.getProperty("deviceName");
            String systemUdid = System.getProperty("udid");
            Properties prop = new Properties();
            FileReader propertyFile = new FileReader(Paths.get("").toAbsolutePath() + "/src/main/resources/" + property + ".properties");
            prop.load(propertyFile);
            platformName = prop.getProperty("platformName");
            platformVersion = prop.getProperty("platformVersion");
            platformVersion = (systemPlatformVersion == null ? prop.getProperty("platformVersion") : systemPlatformVersion);
            deviceName = prop.getProperty("deviceName");
            deviceName = (systemDeviceName == null ? prop.getProperty("deviceName") : systemDeviceName);
            automationName = prop.getProperty("automationName");
            serviceURL = prop.getProperty("serviceURL");
            udid = prop.getProperty("udid");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getAutomationName() {
        return automationName;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public String getUdid() {
        return udid;
    }
}
