package Steps;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static Steps.BeforeStep.driver;

public class TakeScreenshotStep {

    public static void takeScreenshot(String NameStep) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File("target/screenshots/" + NameStep + "screenshot" + java.util.UUID.randomUUID() + ".png"));
    }
}
