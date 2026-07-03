package basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeTest
    public void setDriver() {
        logger.info("Initializing Chrome Driver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = ConfigReader.getProperty("website_url");
        logger.info("Launching URL: {}", url);
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Browser maximized successfully");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("Driver setup completed");
    }

    @AfterTest
    public void tearDown() {
        logger.info("Closing browser");
        if(driver != null){
            driver.quit();
        }
        logger.info("Browser closed successfully");
    }
}
