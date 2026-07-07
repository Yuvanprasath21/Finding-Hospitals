package basepage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected final WebDriver driver;
    protected final Logger logger;
    protected final JavascriptExecutor js;
    protected final WebDriverWait wait;
    protected final FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.logger = LogManager.getLogger(getClass());
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(2));
        PageFactory.initElements(driver,this);
    }

    protected String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    protected void scrollIntoCenterView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    protected void clickByJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}
