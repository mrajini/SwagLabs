package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utils;
import static utilities.ConfigReader.prop;

public class LoginPage extends Utils {
    private WebDriver driver;

    // Locators
    private By inputUsername = By.id("user-name");
    private By inputPassword = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("#login_button_container > div > form > h3");
    private By logoutBtn = By.id("logout_sidebar_link");
    private By burgerMenu = By.cssSelector("#menu_button_container > div > div:nth-child(3) > div");



    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver= driver;
    }

    // Methods
    public void enterUserName(String username) {
        WebElement usernameInput = driver.findElement(inputUsername);
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(inputPassword);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginBtn);
        loginButton.click();
    }

    public void checkLogOutLink(){
        clickBy(burgerMenu);
        clickOnElement(logoutBtn);
       driver.findElement(loginBtn).isDisplayed();
    }

    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);

    }

    public String captureErrorMessage(String error){
        return (driver.findElement(errorMsg).getText());

    }

    public void loginSuccessfully() {
            try {
                String username = prop.getProperty("username");
                String password = prop.getProperty("pwd");
                driver.findElement(inputUsername).sendKeys(username);
                driver.findElement(inputPassword).sendKeys(password);
                clickLoginButton();
            } catch (Exception e) {
                System.out.println("Login failed: " + e.getMessage());
            }
        }
    }



