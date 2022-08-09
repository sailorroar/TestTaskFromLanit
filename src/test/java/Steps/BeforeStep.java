package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import java.io.IOException;
import java.time.Duration;

import static Steps.TakeScreenshotStep.takeScreenshot;

public class BeforeStep {

    public static WebDriver driver;

    @Before
    public static void initDriver() {
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @When("Открываем сайт {string}")
    public void openWebSite(String url) {
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            takeScreenshot("openWebSite");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
