package basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected Properties properties;

    // Initialize logger here itself
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass
    public void setDriver() throws IOException {

        FileReader fileReader =
                new FileReader(".//src//test//resources//config.properties");

        properties = new Properties();
        properties.load(fileReader);

        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        driver.get(properties.getProperty("login_url"));
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {

        File screenshotsDir =
                new File(System.getProperty("user.dir") + "/screenshots");

        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File destination =
                new File(screenshotsDir, fileName + ".png");

        FileHandler.copy(src, destination);
    }
}