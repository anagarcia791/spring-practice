package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"userInterface", "pages", "aspects", "runner", "steps", "model", "scenarioUtils", "utils", "actions", "configuration", "appiumUtils", "driver"})
public class TestContextConfiguration {
}
