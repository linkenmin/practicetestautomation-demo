package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import pages.LoginPage;
import pages.SecurePage;
import utils.ChromeUtils;
import utils.FirefoxUtils;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class CommonSteps {
    WebDriver driver;
    LoginPage loginPage;
    SecurePage securePage;

    @Before
    public void setUp(io.cucumber.java.Scenario scenario) {
        Set<String> tags = new HashSet<>(scenario.getSourceTagNames());
        
        if (tags.contains("@Chrome")) {
            driver = ChromeUtils.initDriver();
        } else if (tags.contains("@Firefox")) {
            driver = FirefoxUtils.initDriver();
        } else {
            throw new IllegalArgumentException("Missing browser tag (@Chrome or @Firefox) in scenario: " + scenario.getName());
        }

        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        securePage = new SecurePage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I launch the login page")
    public void launchLoginPage() {
        loginPage.navigateTo();
        assertTrue("Login page is not ready", loginPage.isReady());
    }

    @When("I login with username {string} and password {string}")
    public void userLogin(String username, String password) {
        loginPage.login(username, password);
    }

    @When("I click the logout button")
    public void userLogout() {
        securePage.logout();
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessage(String message) {
        assertTrue("Expected error message not displayed", loginPage.isErrorDisplayed(message));
    }

    @Then("I should see the login page again")
    public void verifyLoginPage() {
        assertTrue("Login page is not ready", loginPage.isReady());
    }

    @Then("I should see the secure page")
    public void verifySecurePage() {
        assertTrue("Secure page is not ready", securePage.isReady());
    }
}
