package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshoter {
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public static void takeScreenshot() {
        WebDriver driver = WebDriverSingleton.getWebDriverInstance();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);{
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            try {
                FileUtils.copyFile(screenshot, copy);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
