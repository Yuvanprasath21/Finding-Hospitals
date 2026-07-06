package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@data-input-box-id='omni-searchbox-locality']")
    WebElement locationSearchBox;

    @FindBy(xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
    WebElement serviceSearchBox;

    @FindBy(xpath = "//div[@data-qa-id='omni-suggestion-entire-city']")
    WebElement searchInEntireCity;

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public void findAndClickCity(String city) {
        city = capitalize(city);
        locationSearchBox.click();
        locationSearchBox.sendKeys(Keys.CONTROL + "a");
        locationSearchBox.sendKeys(Keys.DELETE);
        locationSearchBox.sendKeys(city);
        try {
            WebElement citySuggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@data-qa-id='omni-suggestion-city']/span/div[contains(.,'" + city + "')]")));
            citySuggestion.click();
        } catch (Exception e) {
            WebElement citySuggestion = wait.until(ExpectedConditions
                    .elementToBeClickable(searchInEntireCity));
            citySuggestion.click();
        }
    }

    public void findAndClickService(String service) {
        service = capitalize(service);

        // 🔑 Focus + clear search box
        wait.until(ExpectedConditions.elementToBeClickable(serviceSearchBox)).click();
        serviceSearchBox.sendKeys(Keys.CONTROL + "a");
        serviceSearchBox.sendKeys(Keys.DELETE);

        // Type character by character to trigger suggestion API
        for (char c : service.toCharArray()) {
            serviceSearchBox.sendKeys(String.valueOf(c));
        }

        String[] xpaths = {
                "//div[@data-qa-id='omni-suggestion-listing'][contains(.,'" + service + "') and contains(.,'TYPE')]",
                "//div[@data-qa-id='omni-suggestion-listing'][contains(.,'" + service + "') and (contains(.,'TYPE') or contains(.,'Type'))]",
                "//div[@data-qa-id='omni-suggestion-listing'][contains(.,'" + service + "')]",
                "//div[@data-qa-id='omni-suggestion-category-type']//div[contains(.,'" + service + "')]",
                "(//div[@data-qa-id='omni-suggestion-listing'])[1]"
        };

        // 🔑 Use JavaScript click — bypasses stale reference & overlay issues
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        boolean clicked = false;
        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries && !clicked; attempt++) {
            for (String xp : xpaths) {
                try {
                    WebElement fresh = wait.until(
                            ExpectedConditions.presenceOfElementLocated(By.xpath(xp)));
                    jsExec.executeScript("arguments[0].click();", fresh);
                    System.out.println("✅ Service clicked via JS: " + xp + " (attempt " + attempt + ")");
                    clicked = true;
                    break;
                } catch (Exception ignored) {
                    // try next XPath
                }
            }
        }

        if (!clicked) {
            System.out.println("⚠️ No suggestion clickable — pressing Enter as fallback");
            serviceSearchBox.sendKeys(Keys.ENTER);
        }

        // 🔑 CRITICAL: Wait for search results page to fully load
        // (dropdown must close AND hospital listing must appear)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@data-qa-id='omni-suggestion-listing']")));
        } catch (Exception e) {
            System.out.println("⚠️ Suggestion dropdown still visible: " + e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//ol//a[@target='_blank']")));
            System.out.println("✅ Search results page loaded");
        } catch (Exception e) {
            System.out.println("⚠️ Hospital listing not fully loaded: " + e.getMessage());
        }
    }
}