package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import utilities.Hooks;
import static stepDefs.HomeStepDefs.actualTotalPrice;



public class CartStepDefs {
    WebDriver driver;
    CartPage cartPage;
    HomePage homePage;

    public CartStepDefs(){
        this.driver = Hooks.getDriver();
        this.cartPage = new CartPage(driver);
        this.homePage = new HomePage(driver);
    }
    @And("I navigate to checkout overview page")
    public void iNavigateToCheckoutOverviewPage() {
        cartPage.navToCheckoutPage();

    }

    @Then("I should see the correct total price and a Finish button")
    public void i_should_see_correct_total_price_and_finish_button() {
        double displayedTotalPrice = cartPage.getDisplayedTotalPrice();
        Assert.assertEquals(displayedTotalPrice, actualTotalPrice, "Total price is incorrect");
        Assert.assertTrue(cartPage.verifyFinishButtonIsDisplayed(),"Finish button is displayed");

    }
    @And("I submit the order with all the required details")
    public void iSubmitTheOrderWithAllTheRequiredDetails() {
        cartPage.placeOrder();
    }
    @Then("I should see order been placed successfully")
    public void i_should_see_order_been_placed_successfully() {
    Assert.assertTrue(cartPage.isOrderCompleted(),"Order completed successfully");
    }
    @Then("I should see the Thank you message")
    public void i_should_see_the_thank_you_message() {
    Assert.assertEquals(cartPage.getSuccessMessage(),"THANK YOU FOR YOUR ORDER");
    }

}

