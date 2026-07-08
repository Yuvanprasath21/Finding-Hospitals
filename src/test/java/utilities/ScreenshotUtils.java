package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static void takeScreenShot(WebDriver driver, String fileName) {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized");
        }
        File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotsDir, fileName + ".png");
        try {
            FileHandler.copy(src, destination);
        } catch (IOException e) {
            // pass
        }
    }
}
