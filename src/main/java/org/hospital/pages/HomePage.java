package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//a[contains(@data-qa-id,'footer-item')]//span[text()='Read about medicines']")
    WebElement medicine;

    @FindBy(xpath = "//a[contains(@data-qa-id,'footer-item')]//span[text()='Wellness Plans']")
    WebElement wellness;

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public void findAndClickCity(String city) {

        city = capitalize(city);// toUpperCase
        locationSearchBox.click();
        locationSearchBox.sendKeys(Keys.CONTROL + "a");
        locationSearchBox.sendKeys(Keys.DELETE);
        locationSearchBox.sendKeys(city);
        try{
            WebElement citySuggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@data-qa-id='omni-suggestion-city']/span/div[contains(.,'" + city + "')]")));
            citySuggestion.click();
        }
        catch (Exception e) {
            WebElement citySuggestion = wait.until(ExpectedConditions
                    .elementToBeClickable(searchInEntireCity));
            citySuggestion.click();
        }
    }

    public void findAndClickService(String service) {

        service = capitalize(service);
        serviceSearchBox.sendKeys(service);
        WebElement serviceSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@data-qa-id='omni-suggestion-listing']" +
                                "[contains(.,'" + service + "') and contains(.,'TYPE')]")
                ));
        serviceSuggestion.click();
    }

    public void clickMedicinesReportPage(){
        js.executeScript("arguments[0].scrollIntoView(true);", medicine);
        wait.until(ExpectedConditions.elementToBeClickable(medicine)).click();
    }

    public void switchToNewTab() {
        String originalTab = driver.getWindowHandle();
        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    public void clickWellness(){
        js.executeScript("arguments[0].scrollIntoView(true);", wellness);
        wait.until(ExpectedConditions.elementToBeClickable(wellness)).click();
    }

}
