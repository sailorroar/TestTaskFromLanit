package Steps.pageSteps;

import Helpers.Helpers;
import WaitHelpers.WaitHelper;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static Steps.BeforeStep.driver;
import static Steps.TakeScreenshotStep.takeScreenshot;

public class ProductSelectionPageStep {

    private final By applyButton        = By.xpath("//button[text()=\"Применить\"]");
    private final By showAll            = By.xpath("//span[contains(text(),'Показать всё')]");
    private final By defaultSorting     = By.xpath("//span[contains(text(),'Сначала недорогие')]/..");
    private final By productLoadSpinner = By.xpath("//div[@class=\"catalog-preloader catalog-preloader_active\"]");

    /**
     * метод ждет пока пропадет спиннер загрузки продуктов (не более 10 секунд)
     **/
    public void waitingForAFullLadOfProduct () {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(productLoadSpinner));
    }

    /**
     * Костыльный метод для выбора параметров продукта
     * Скролит на одну текущую высоту экрана
     *
     * т.к не работает  scrollToElement
     *                  (JavaScript) scrollIntoView
     *
     * Принимает локатор элемента в качестве аргумента
     **/
    public void searchAndClickElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Helpers help = new Helpers();
        if(help.isElementPresent(by)) {
            driver.findElement(by).click();
        } else {
            js.executeScript("window.scrollBy(0,document.documentElement.clientHeight);");
        }
    }

    @When("Отфильтровать список смартфонов по {string} -> {string}")
    public void FilterTheListOfPhone(String Name, String Params) {
        By nameLocator = By.xpath("//span[contains(text(),'" + Name + "')]/..");
        By paramsLocator = By.xpath("//span[text()=\"" + Params + "  \"]");

        searchAndClickElement(nameLocator);

        WebElement element = driver.findElement(nameLocator);
        String test = element.getAttribute("class");
        if(test.equals("ui-link ui-collapse__link_left ui-collapse__link ui-collapse__link_list")) {
            element.click();
        }

        //isElementPresent не подходит так как элемент есть в DOM, но не имеет размера
        boolean isPresent = driver.findElements(showAll).size() > 0;
        if (isPresent) {
            driver.findElement(showAll).click();
        }
        searchAndClickElement(paramsLocator);
        try {
            takeScreenshot("FilterTheListOfPhone");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @When("Принять фильтры")
    public void applyFilters() {
        searchAndClickElement(applyButton);
        try {
            takeScreenshot("applyFilters");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @When("Отсортировать список смартфонов {string}")
    public void sorting(String sortingType) {
        By sortingTypeLocator = By.xpath("//span[contains(text(),'" + sortingType + "')]/..");
        waitingForAFullLadOfProduct();
        WaitHelper wait = new WaitHelper();
        wait.waitElementToBeClickable(defaultSorting);
        wait.waitElementToBeClickable(sortingTypeLocator);
        try {
            takeScreenshot("sorting");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @When("Выбрать первый смартфон в списке и перейти на страницу товара")
    public void selectionProduct() {
        waitingForAFullLadOfProduct();
        new WaitHelper().waitElementToBeClickable(By.xpath("//a[@class=\"catalog-product__name ui-link ui-link_black\"]"));
        try {
            takeScreenshot("selectionProduct");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
