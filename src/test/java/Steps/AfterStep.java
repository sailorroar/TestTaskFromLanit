package Steps;

import io.cucumber.java.After;

import static Steps.BeforeStep.driver;

public class AfterStep {

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
