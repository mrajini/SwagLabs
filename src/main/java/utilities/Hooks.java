package utilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Hooks  {

    public static WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriver setDriver() {
        return driver;
    }

    public WebDriver init_driver(String browser) {
        System.out.println("browser name is:" + browser);
        if (browser.equals("firefox")) {
            return new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            return new ChromeDriver();
        } else if (browser.equals("safari")) {
            return new SafariDriver();
        } else {
            System.out.println("Please provide the correct browser name" + browser);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return setDriver();
    }


    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driver = init_driver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            /*take screenshot for failed scenario*/
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

        }

    }
}

