package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticsPage extends BasePage {

    public DiagnosticsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href,'/doctors')]")
    WebElement findDoctorsNearYou;

    @FindBy(xpath = "//a[@data-qa-id='popular-search-item']")
    List<WebElement> popularSearches;

    @FindBy(xpath = "//a[contains(@href,'tests')]")
    WebElement diagnosticsLink;

    @FindBy(xpath = "//div[normalize-space()='TOP CITIES']/following-sibling::ul/li//div[contains(@class,'o-f-color--primary')]")
    List<WebElement> topCities;

    @FindBy(xpath = "//div[text()='ALL CITIES']/following-sibling::div[contains(@class,'city-selector__city')]")
    List<WebElement> allCities;

    @FindBy(xpath = "//div[text()='Bangalore']")
    WebElement bangaloreCity;

    @FindBy(xpath = "(//a[contains(@href,'/tests/')])[1]")
    WebElement firstTestCard;

    @FindBy(xpath = "(//a[contains(@href,'/tests/')])[2]")
    WebElement secondTestCard;

    @FindBy(xpath = "//div[normalize-space()='Add to Cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//div[text()='Fever']")
    WebElement feverCard;

    @FindBy(xpath = "(//span[@data-aid='m-popular-package-book'])[1]")
    WebElement bookButton;

    @FindBy(xpath = "//input[@data-aid='patient-name']")
    WebElement patientName;

    @FindBy(xpath = "//input[@data-aid='patient-age']")
    WebElement patientAge;

    @FindBy(xpath = "//input[@data-aid='patient-mobile']")
    WebElement patientMobile;

    @FindBy(xpath = "//input[@data-aid='patient-email']")
    WebElement patientEmail;

    @FindBy(xpath = "//div[contains(text(),'Invalid Phone Number')]")
    WebElement invalidPhoneMsg;

    @FindBy(xpath = "//div[contains(text(),'Enter valid email')]")
    WebElement invalidEmailMsg;

    public void clickDiagnostics() {
        clickByJS(diagnosticsLink);
    }

    public void clickFindDoctorsNearYou() {
        clickByJS(findDoctorsNearYou);
    }

    public void selectBangalore() {
        clickByJS(bangaloreCity);
    }

    public void clickFeverCard() {
        wait.until(ExpectedConditions.visibilityOf(feverCard));
        clickByJS(feverCard);
    }

    public void clickBookButton() {
        wait.until(ExpectedConditions.visibilityOf(bookButton));
        scrollIntoCenterView(bookButton);
        wait.until(ExpectedConditions.elementToBeClickable(bookButton));
        try {
            bookButton.click();
        } catch (Exception e) {
            clickByJS(bookButton);
        }
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        clickByJS(addToCartButton);
    }

    public List<String> getPopularSearches() {
        wait.until(ExpectedConditions.visibilityOfAllElements(popularSearches));
        List<String> searches = new ArrayList<>();
        for (WebElement search : popularSearches) {
            searches.add(search.getText().trim());
        }
        return searches;
    }

    public List<String> getTopCities() {
        List<String> cities = new ArrayList<>();
        for (WebElement city : topCities) {
            cities.add(city.getText().trim());
        }
        return cities;
    }

    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>();
        for (WebElement city : allCities) {
            cities.add(city.getText().trim());
        }
        return cities;
    }

    public void enterPatientName(String name) {
        wait.until(ExpectedConditions.urlContains("user-details"));
        wait.until(ExpectedConditions.visibilityOf(patientName));
        patientName.clear();
        patientName.sendKeys(name);
    }

    public void enterPatientAge(String age) {
        patientAge.clear();
        patientAge.sendKeys(age);
    }

    public void enterMobile(String mobile) {
        patientMobile.clear();
        patientMobile.sendKeys(mobile);
    }

    public void enterEmail(String email) {
        patientEmail.clear();
        patientEmail.sendKeys(email);
    }

    public String getInvalidPhoneMessage() {
        return invalidPhoneMsg.getText();
    }

    public String getInvalidEmailMessage() {
        return invalidEmailMsg.getText();
    }

    public boolean isDiagnosticsPageOpened() {
        wait.until(ExpectedConditions.urlContains("tests"));
        return driver.getCurrentUrl().contains("tests");
    }

    public boolean isDoctorsPageOpened() {
        wait.until(ExpectedConditions.urlContains("doctors"));
        return driver.getCurrentUrl().contains("doctors");
    }

    public boolean isTestDetailsPageOpened() {
        wait.until(ExpectedConditions.urlContains("thyroid"));
        return driver.getCurrentUrl().contains("thyroid");
    }

    public boolean isFirstTestCardVisible() {
        wait.until(ExpectedConditions.visibilityOf(firstTestCard));
        return firstTestCard.isDisplayed();
    }

    public void openFirstTestCard() {
        wait.until(ExpectedConditions.visibilityOf(firstTestCard));
        firstTestCard.click();
        wait.until(ExpectedConditions.urlContains("thyroid"));
    }

    public void addFirstTestToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }

    public boolean isSecondTestCardVisible() {
        wait.until(ExpectedConditions.visibilityOf(secondTestCard));
        return secondTestCard.isDisplayed();
    }

    public void openSecondTestCard() {
        wait.until(ExpectedConditions.visibilityOf(secondTestCard));
        clickByJS(secondTestCard);
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public boolean isAddToCartButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        return addToCartButton.isDisplayed();
    }

    public boolean isCartPageOpened() {
        return driver.getCurrentUrl().contains("cart");
    }

    public boolean isFeverCheckupPageOpened() {
        wait.until(ExpectedConditions.urlContains("fever-checkup"));
        return driver.getCurrentUrl().contains("fever-checkup");
    }

    public void triggerEmailValidation() {
        patientEmail.sendKeys(Keys.TAB);
    }

    public boolean areValidationMessagesDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(invalidPhoneMsg));
        wait.until(ExpectedConditions.visibilityOf(invalidEmailMsg));

        return invalidPhoneMsg.isDisplayed()
                && invalidEmailMsg.isDisplayed();
    }

}