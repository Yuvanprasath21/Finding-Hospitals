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

    @FindBy(xpath = "//span[text()='Facebook']/ancestor::a")
    WebElement facebookLink;

    @FindBy(xpath = "//span[text()='Twitter']/ancestor::a")
    WebElement twitterLink;

    @FindBy(xpath = "//span[text()='LinkedIn']/ancestor::a")
    WebElement linkedInLink;

    @FindBy(xpath = "//span[text()='Youtube']/ancestor::a")
    WebElement youtubeLink;

    @FindBy(xpath = "//span[text()='Github']/ancestor::a")
    WebElement githubLink;

    public void findAndClickCity(String city) {

        city = capitalize(city);
        locationSearchBox.click();
        locationSearchBox.sendKeys(Keys.CONTROL + "a");
        locationSearchBox.sendKeys(Keys.DELETE);
        locationSearchBox.sendKeys(city);
        try{
            WebElement citySuggestion = wait.until(ExpectedConditions
                    .elementToBeClickable(searchInEntireCity));
            citySuggestion.click();
        }
        catch (Exception exception1) {
            try {
                WebElement citySuggestion = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[@data-qa-id='omni-suggestion-city']/span/div[contains(.,'" + city + "')]")));
                citySuggestion.click();
            }
            catch (Exception exception2){
                logger.warn("Location selection failed: {}",
                        exception2.getMessage()
                );
            }
        }
    }

    public void findAndClickService(String service) {

        service = capitalize(service);
        serviceSearchBox.sendKeys(service);
        try{
            WebElement serviceSuggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@data-qa-id='omni-suggestion-listing']" +
                                    "[contains(.,'" + service + "') and contains(.,'TYPE')]")
                    ));
            serviceSuggestion.click();
        }
        catch(Exception exception){
            logger.warn("Service selection failed: {}",
                    exception.getMessage()
            );
        }
    }

    public void clickMedicinesReportPage(){
        scrollIntoView(medicine);
        wait.until(ExpectedConditions.elementToBeClickable(medicine)).click();
    }

    public void clickWellness(){
        scrollIntoView(wellness);
        wait.until(ExpectedConditions.elementToBeClickable(wellness)).click();
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

    public boolean verifyFacebookLink() {
        return verifyLink(facebookLink, "facebook");
    }

    public boolean verifyTwitterLink() {
        return verifyLink(twitterLink, "twitter");
    }

    public boolean verifyLinkedInLink() {
        return verifyLink(linkedInLink, "linkedin");
    }

    public boolean verifyYoutubeLink() {
        return verifyLink(youtubeLink, "youtube");
    }

    public boolean verifyGithubLink() {
        return verifyLink(githubLink, "github");
    }

    public boolean verifyLink(WebElement link, String expectedUrlPart) {
        String parentWindow = driver.getWindowHandle();
        try {
            scrollIntoView(link);
            clickByJS(link);
            wait.until(d -> d.getWindowHandles().size() > 1);
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(parentWindow)) {
                    driver.switchTo().window(handle);
                    String currentUrl = driver.getCurrentUrl().toLowerCase();
                    driver.close();
                    driver.switchTo().window(parentWindow);
                    return currentUrl.contains(
                            expectedUrlPart.toLowerCase());
                }
            }
        } catch (Exception e) {
            logger.warn("{} link verification failed: {}",
                    expectedUrlPart,
                    e.getMessage());
        }
        driver.switchTo().window(parentWindow);
        return false;
    }



}
