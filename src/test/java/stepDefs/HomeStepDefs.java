package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import utilities.Hooks;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static utilities.ConfigReader.prop;


public class HomeStepDefs {
    WebDriver driver;
    HomePage homePage;
    CartPage cartPage;
    static double actualTotalPrice;

    public HomeStepDefs() {
        this.driver = Hooks.getDriver();
        this.homePage = new HomePage(driver);
        this.cartPage = new CartPage(driver);
    }

    @Given("I am on the Swag Lab home page")
    public void i_am_on_the_swag_lab_home_page() {
        String expectedTitle = "Swag Labs";
        String actualTitle = homePage.homePageTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @When("I click on the item image")
    public void i_click_on_the_item_image() {
        homePage.clickOnItem();
    }
    @Then("I should be navigated to item details page")
    public void i_should_be_navigated_to_item_details_page() {
        String actualURL = homePage.getDetailsPageURl();
        String expectedURL = prop.getProperty("detailsPageURL");
        Assert.assertTrue(actualURL.equals(expectedURL),"Displaying the details of the selected item");
    }
    @Then("I should see enabled {string} and {string} buttons")
    public void i_should_see_enabled_and_buttons(String addBtn,String backBtn) {
       Assert.assertTrue(homePage.checkForBackButton());
        Assert.assertTrue(homePage.checkForAddToCartButton());
    }

    @When("I select {string},{string},{string}")
    public void i_select(String item1, String item2, String item3) {
        List<String> items = Arrays.asList(item1, item2, item3)
                .stream()
                .filter(item -> !item.trim().isEmpty())
                .collect(Collectors.toList());

        for (String item : items) {

            homePage.addItemToCartByName(item);
            actualTotalPrice = homePage.totalPrice += homePage.getItemPrice(item);

        }
    }
    @Then("I should see {string},{string},{string} in basket")
    public void i_should_see_in_basket(String item1, String item2, String item3) {
        List<String> items = Arrays.asList(item1, item2, item3)
                .stream()
                .filter(item -> !item.trim().isEmpty())
                .collect(Collectors.toList());
       cartPage.navToCartPage();
        for (String item : items) {
          Assert.assertTrue(cartPage.isItemInBasket(item),"Item" + item + " should be in the basket");
        }
    }
    }

