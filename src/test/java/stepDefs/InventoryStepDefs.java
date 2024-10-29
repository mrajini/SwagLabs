package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.InventoryPage;
import utilities.Hooks;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static utilities.ConfigReader.prop;


public class InventoryStepDefs {
    WebDriver driver;
    InventoryPage inventoryPage;
    CartPage cartPage;
    static double actualTotalPrice;

    public InventoryStepDefs() {
        this.driver = Hooks.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
    }

    @Given("I am on the Swag Lab inventory page")
    public void i_am_on_the_swag_lab_inventory_page() {
        String expectedTitle = "Swag Labs";
        String actualTitle = inventoryPage.inventoryPageTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @When("I click on the item image")
    public void i_click_on_the_item_image() {
        inventoryPage.clickOnItem();
    }
    @Then("I should be navigated to item details page")
    public void i_should_be_navigated_to_item_details_page() {
        String actualURL = inventoryPage.getDetailsPageURl();
        String expectedURL = prop.getProperty("detailsPageURL");
        Assert.assertTrue(actualURL.equals(expectedURL),"Displaying the details of the selected item");
    }
    @Then("I should see enabled {string} and {string} buttons")
    public void i_should_see_enabled_and_buttons(String addBtn,String backBtn) {
       Assert.assertTrue(inventoryPage.checkForBackButton());
        Assert.assertTrue(inventoryPage.checkForAddToCartButton());
    }

    @When("I select {string},{string},{string}")
    public void i_select(String item1, String item2, String item3) {
        List<String> items = Arrays.asList(item1, item2, item3)
                .stream()
                .filter(item -> !item.trim().isEmpty())
                .collect(Collectors.toList());

        for (String item : items) {

            inventoryPage.addItemToCartByName(item);
            actualTotalPrice = inventoryPage.totalPrice += inventoryPage.getItemPrice(item);

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

