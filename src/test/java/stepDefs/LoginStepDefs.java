package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.Hooks;
import static utilities.ConfigReader.prop;

public class LoginStepDefs {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    public LoginStepDefs() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
    }

    @Given("I am on the Swag Lab login page")
    public void i_am_on_the_swag_lab_login_page() {
        loginPage.visit(prop.getProperty("url"));
    }

    @Given("I have entered a valid {string} and {string}")
    public void iHaveEnteredAValidAnd(String username, String password) {
        loginPage.login(username, password);
    }


    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully and see {string}")
    public void iShouldBeLoggedInSuccessfullyAndSee(String expectedTitle) {
        String actualTitle = inventoryPage.inventoryPageTitle();
        ;Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Expected title to contain: " + expectedTitle + " but found: " + actualTitle);
    }

    @Given("I have entered invalid {string} and {string}")
    public void iHaveEnteredInvalidAnd(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see an error message indicating {string}")
    public void iShouldSeeAnErrorMessageIndicating(String expectedErrorMsg) {
        String actualErrorMsg = loginPage.captureErrorMessage(expectedErrorMsg);
        Assert.assertTrue(true, actualErrorMsg);
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Given("I have logged in successfully")
    public void i_have_logged_in_successfully() {
        loginPage.loginSuccessfully();
    }

    @When("I click on the logout button")
    public void i_click_on_the_logout_button() {
        loginPage.checkLogOutLink();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        String loginPageURL = prop.getProperty("url");
        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);

    }

}




