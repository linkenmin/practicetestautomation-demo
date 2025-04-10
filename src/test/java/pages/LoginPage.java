package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "https://practicetestautomation.com/practice-test-login/";

    private final By usernameLocator = By.id("username");
    private final By passwordLocator = By.id("password");
    private final By submitLocator = By.id("submit");
    private final By errorLocator = By.id("error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateTo() {
        driver.get(url);
    }

    public boolean isAt() {
        return driver.getCurrentUrl().equals(url);
    }

    public boolean isUsernameFieldDisplayed() {
        return getUsernameField().isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return getPasswordField().isDisplayed();
    }

    public boolean isReady() {
        return isAt() && isUsernameFieldDisplayed() && isPasswordFieldDisplayed();
    }

    public void login(String username, String password) {
        getUsernameField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getSubmitButton().click();
    }    

    public boolean isErrorDisplayed(String message) {
        return getErrorMessageElement().getText().contains(message);
    }

    private WebElement getUsernameField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
    }
    
    private WebElement getPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
    }
    
    private WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(submitLocator));
    }    

    private WebElement getErrorMessageElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
    }
}
