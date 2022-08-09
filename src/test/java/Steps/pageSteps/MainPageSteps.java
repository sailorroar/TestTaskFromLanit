package Steps.pageSteps;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import java.io.IOException;

import static Steps.BeforeStep.driver;
import static Steps.TakeScreenshotStep.takeScreenshot;

public class MainPageSteps {

    @When("Переходим в раздел {string} -> {string}")
    public void selectionOfTest(String selection1, String selection2) {
        driver.findElement(By.xpath("//a[contains(text(),'" + selection1 + "')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'" + selection2 + "')]")).click();
        try {
            takeScreenshot("selectionOfTest");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
