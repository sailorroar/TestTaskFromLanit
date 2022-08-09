package Steps.pageSteps;

import Helpers.Helpers;
import Steps.TakeScreenshotStep;
import WaitHelpers.WaitHelper;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static Steps.BeforeStep.driver;
import static Steps.TakeScreenshotStep.*;

public class ProductPage {

    private final By expandEverything   = By.xpath("//button[contains(text(),'Развернуть все')]");

    @When("Выполнить проверки данных в разделе Характеристики по {string} -> {string}")
    public void performanceComparison (String type, String value) {
        Helpers help = new Helpers();
        if(help.isElementPresent(expandEverything)) {
            new WaitHelper().waitElementToBeClickable(expandEverything);
        }
        WebElement typeBlock = driver.findElement(By.xpath("//div[text()=' " + type + " ']"));
        String actual = typeBlock.findElement(By.xpath("//div[text()=' " + value + "']")).getText();
        Assert.assertEquals(value, actual);
        try {
            takeScreenshot("performanceComparison");
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
