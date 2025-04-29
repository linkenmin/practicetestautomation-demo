package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SecurePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "https://practicetestautomation.com/logged-in-successfully/";

    private final By logoutButtonLocator = By.xpath("//a[text()='Log out']");

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }

    public boolean isAt() {
        return driver.getCurrentUrl().equals(url);
    }

    public boolean isLogoutButtonDisplayed() {
        return getLogoutButton().isDisplayed();
    }

    public boolean isReady() {
        return isAt() && isLogoutButtonDisplayed();
    }

    public void logout() {
        getLogoutButton().click();
    }

    private WebElement getLogoutButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButtonLocator));
    }
}
