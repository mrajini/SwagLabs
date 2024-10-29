package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utils;

public class InventoryPage extends Utils {
private WebDriver driver;
    public double totalPrice = 0.0;

    private By item1 = By.cssSelector("#item_1_img_link > img");
    private By backButton = By.cssSelector("#inventory_item_container > div > button");
    private By addToCartButton = By.xpath("//*[text()=\"ADD TO CART\"]");
    public InventoryPage(WebDriver driver){
       this.driver = driver;
    }
    public String inventoryPageTitle(){
       return driver.getTitle();

    }
    public void clickOnItem(){
        clickOnElement(item1);

    }
    public String getDetailsPageURl(){
        String actualUrl= driver.getCurrentUrl();
        return actualUrl;
    }

    public boolean checkForBackButton(){
        driver.findElement(backButton).isEnabled();
        isElementPresent(backButton);
        return true;
    }
    public boolean checkForAddToCartButton(){
        driver.findElement(addToCartButton).isEnabled();
        isElementPresent(addToCartButton);
        return true;
    }

    public void addItemToCartByName(String itemName){
        WebElement item = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']" +
                "/ancestor::div[@class='inventory_item']//button[text()='ADD TO CART']"));
        clickElement(item);

    }
    public double getItemPrice(String itemName) {
        String priceText = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']" +
                "/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']")).getText();
        System.out.println("type...................." + priceText);
        return Double.parseDouble(priceText.replace("$", ""));
    }

    }

