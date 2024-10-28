package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Utils {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    public void visit(String URL) {
        driver.get(URL);
    }
    public void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilWebElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickBy(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public WebElement find(By by) {
        return driver.findElement(by);
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickOnElement(By element) {
        waitUntilVisible(element);
        WebElement ele = find(element);
        scrollIntoView(ele);
        waitUntilElementClickable(element);
        driver.findElement(element).click();
    }

    public void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

}

