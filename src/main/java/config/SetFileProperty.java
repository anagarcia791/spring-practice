package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class SetFileProperty {
    private static final Logger logger = LoggerFactory.getLogger(SetFileProperty.class);
    private static ThreadLocal<ConfigProperties> configProperties = new ThreadLocal<>();

    /**
     * This method gets the property file follow the thread
     *
     * @return ConfigProperties
     */
    public static ConfigProperties getConfigProperties() {
        if (null == configProperties.get()) {
            logger.info("CONSIGUIENDO PROPERTIES..........");
            System.out.println("CONSIGUIENDO PROPERTIES..........");
            setConfigProperties();
        }
        return configProperties.get();
    }

    /**
     * This method distributes the property file follow the thread
     */
    public static void setConfigProperties() {
        String[] properties = System.getProperty("property").trim().split(",");
        IntStream streamRange = IntStream.range(0, properties.length);
        String property = properties[streamRange.filter(i -> Thread.currentThread().getName().contains(Integer.toString(i))).findFirst().orElse(0)];
        configProperties.set(new ConfigProperties(property));
    }
}
