package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeUtils {
    public static WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();

        String headless = System.getProperty("headless", "true");
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }
}
