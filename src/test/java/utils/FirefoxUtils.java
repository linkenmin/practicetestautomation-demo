package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxUtils {
    public static WebDriver initDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}