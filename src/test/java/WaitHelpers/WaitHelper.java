package WaitHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Steps.BeforeStep.driver;

public class WaitHelper {

    /**
     * Ожидание пока элемент не будет кликабельный
     * Принимает локатор элемента в качестве аргумента
     **/
    public void waitElementToBeClickable(By by) {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        wait.click();
    }
}
