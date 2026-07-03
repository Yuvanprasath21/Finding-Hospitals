package basepage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;
    protected final Logger logger;

    protected static final int TARGET_COUNT = 10;
    protected static final int MAX_SCROLL_COUNT = 20;
    protected static final double MIN_RATING = 3.5;
    protected static final String OPEN_24_X_7 = "Open 24x7";
    protected static final String HOSPITAL_LIST = "//li";
    protected static final String HOSPITAL_NAME = ".//h2";
    protected static final String HOSPITAL_RATING = ".//div[@class='c-feedback']/div/span[@class='u-bold']";
    protected static final String BOOK_VISIT_BUTTON = ".//button[@class='c-book-cta']";

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.logger = LogManager.getLogger(getClass());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    protected String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void clickByJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}
