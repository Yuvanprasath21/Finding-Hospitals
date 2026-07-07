package basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hospital.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;


import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeClass
    public void setDriver() {
        logger.info("Initializing Chrome Driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = ConfigReader.getProperty("website_url");
        logger.info("Launching URL: {}", url);
        driver.get(url);
        logger.info("Browser maximized successfully");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("Driver setup completed");
    }

    protected void navigateToSearchResults() {
        HomePage homePage = new HomePage(driver);

        String city = ConfigReader.getProperty("city");
        logger.info("Selecting city: {}", city);
        homePage.findAndClickCity(city);

        String service = ConfigReader.getProperty("service");
        logger.info("Selecting service: {}", service);
        homePage.findAndClickService(service);
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing browser");
        if(driver != null){
            driver.quit();
        }
        logger.info("Browser closed successfully");
    }
}
