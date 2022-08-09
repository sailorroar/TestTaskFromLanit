package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static Steps.BeforeStep.driver;

public class Helpers {
    /**
     * метод возвращает true если элемент присутствует в DOM
     * Принимает локатор элемента в качестве аргумента
     **/
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
