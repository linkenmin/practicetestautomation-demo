package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxUtils {
    public static WebDriver initDriver() {
        FirefoxOptions options = new FirefoxOptions();

        String headless = System.getProperty("headless", "true");
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        return new FirefoxDriver(options);
    }
}
