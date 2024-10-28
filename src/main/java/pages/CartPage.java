package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import static utilities.ConfigReader.prop;

public class CartPage extends Utils {

    private WebDriver driver;
    private By cartBtn = By.cssSelector("#shopping_cart_container > a > svg > path");
    private By checkoutBtn = By.cssSelector("#cart_contents_container > div > div.cart_footer > a.btn_action.checkout_button");
    private By inputFirstName = By.id("first-name");
    private By inputLastName = By.id("last-name");
    private By inputZipCode = By.id("postal-code");
    private By continueBtn = By.cssSelector("#checkout_info_container > div > form > div.checkout_buttons > input");
    private By finishBtn = By.xpath("//*[text()=\"FINISH\"]");
    private By succMsg = By.cssSelector("#checkout_complete_container > h2");
    private By completeChkOut = By.cssSelector("#checkout_complete_container > div");
    private By ponyExpressImg = By.cssSelector("#checkout_complete_container > img");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navToCartPage() {
        clickBy(cartBtn);
    }

    public boolean isItemInBasket(String itemName) {
        WebElement item = driver.findElement(By.xpath("//*[@id='cart_contents_container']//*[contains(text(),'" + itemName + "')]"));
        return item.isDisplayed();

    }

    public void clickOnCheckout() {
        clickOnElement(checkoutBtn);
    }

    public void fillAddress() {
        try {
            driver.findElement(inputFirstName).sendKeys(prop.getProperty("firstname"));
            driver.findElement(inputLastName).sendKeys(prop.getProperty("lastname"));
            driver.findElement(inputZipCode).sendKeys(prop.getProperty("zipcode"));
            clickOnElement(continueBtn);
        } catch (Exception e) {
            System.out.println("Failed to fill address");
        }
    }

    public void navToCheckoutPage() {
        navToCartPage();
        clickOnCheckout();
        fillAddress();
    }

    public double getDisplayedTotalPrice() {
        WebElement totalElement = driver.findElement(By.className("summary_subtotal_label"));
        String totalText = totalElement.getText().replace("Item total: $", "");
        return Double.parseDouble(totalText);
    }

    public boolean verifyFinishButtonIsDisplayed() {
        isElementPresent(finishBtn);
        return true;
    }

    public void placeOrder(){
        navToCheckoutPage();
        clickOnElement(finishBtn);
    }
    public String  getSuccessMessage(){
        return driver.findElement(succMsg).getText();
    }
    public boolean isOrderCompleted(){
        isElementPresent(completeChkOut);
        isElementPresent(ponyExpressImg);
        return true;
    }
}
