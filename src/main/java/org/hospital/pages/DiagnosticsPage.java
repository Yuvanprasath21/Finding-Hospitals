package org.hospital.pages;

import basepage.BasePage;
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
    private WebElement findDoctorsNearYou;

    @FindBy(xpath = "//a[@data-qa-id='popular-search-item']")
    private List<WebElement> popularSearches;

    @FindBy(xpath = "//a[contains(@href,'tests')]")
    private WebElement diagnosticsLink;

    @FindBy(xpath = "//div[normalize-space()='TOP CITIES']")
    private WebElement topCitiesHeading;

    @FindBy(xpath = "//div[normalize-space()='ALL CITIES']")
    private WebElement allCitiesHeading;

    @FindBy(xpath = "//div[normalize-space()='TOP CITIES']/following-sibling::ul/li//div[contains(@class,'o-f-color--primary')]")
    private List<WebElement> topCities;

    @FindBy(xpath = "//div[text()='ALL CITIES']/following-sibling::div[contains(@class,'city-selector__city')]")
    private List<WebElement> allCities;

    @FindBy(xpath = "//div[text()='Bangalore']")
    private WebElement bangaloreCity;

    @FindBy(xpath = "(//a[contains(@href,'/tests/')])[1]")
    private WebElement firstTestCard;

    @FindBy(xpath = "(//a[contains(@href,'/tests/')])[2]")
    private WebElement secondTestCard;

    @FindBy(xpath = "//div[normalize-space()='Add to Cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[contains(@class,'c-global-cart')]")
    private WebElement cartButton;

    @FindBy(xpath = "//div[contains(@class,'cart-item')]")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//div[text()='Fever']")
    private WebElement feverCard;

    @FindBy(xpath = "(//span[@data-aid='m-popular-package-book'])[1]")
    private WebElement bookButton;

    @FindBy(xpath = "//input[@data-aid='patient-name']")
    private WebElement patientName;

    @FindBy(xpath = "//input[@data-aid='patient-age']")
    private WebElement patientAge;

    @FindBy(xpath = "//input[@data-aid='patient-mobile']")
    private WebElement patientMobile;

    @FindBy(xpath = "//input[@data-aid='patient-email']")
    private WebElement patientEmail;

    @FindBy(xpath = "//div[contains(text(),'Invalid Phone Number')]")
    private WebElement invalidPhoneMsg;

    @FindBy(xpath = "//div[contains(text(),'Enter valid email')]")
    private WebElement invalidEmailMsg;

    public void clickDiagnostics() {
        clickByJS(diagnosticsLink);
    }

    public void clickFindDoctorsNearYou() {
        clickByJS(findDoctorsNearYou);
    }

    public void selectBangalore() {
        clickByJS(bangaloreCity);
    }

    public void clickFirstTestCard() {
        clickByJS(firstTestCard);
    }

    public void clickSecondTestCard() {
        clickByJS(secondTestCard);
    }

    public void clickFeverCard() {
        slowScrollToElement(feverCard);
        wait.until(ExpectedConditions.visibilityOf(feverCard));
        clickByJS(feverCard);
    }

    public void clickBookButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("(//span[@data-aid='m-popular-package-book'])[1]")
        ));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", bookButton);

        wait.until(ExpectedConditions.elementToBeClickable(bookButton));

        try {
            bookButton.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", bookButton);
        }

        // Wait for navigation to user-details page
        wait.until(ExpectedConditions.urlContains("user-details"));

        // Then wait for the patient-name field on that page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//input[@data-aid='patient-name']")
        ));

        System.out.println("Patient details page loaded: " + driver.getCurrentUrl());
    }

    public void clickAddToCart() {
        slowScrollToElement(addToCartButton);
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        clickByJS(addToCartButton);
    }

    public List<String> getPopularSearches() {
        List<String> searches = new ArrayList<>();
        for (WebElement search : popularSearches) {
            searches.add(search.getText().trim());
        }
        return searches;
    }

    public String getTopCitiesHeading() {
        return topCitiesHeading.getText();
    }

    public String getAllCitiesHeading() {
        return allCitiesHeading.getText();
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

    public WebElement getInvalidPhoneMessageElement() {
        return invalidPhoneMsg;
    }

    public WebElement getInvalidEmailMessageElement() {
        return invalidEmailMsg;
    }

    public WebElement getEmailElement() {
        return patientEmail;
    }

    public WebElement getPatientNameElement() {
        return patientName;
    }

    public WebElement getFeverCardElement() {
        return feverCard;
    }

    public WebElement getBookButtonElement() {
        return bookButton;
    }

    public WebElement getFirstTestCardElement() {
        return firstTestCard;
    }

    public WebElement getSecondTestCardElement() {
        return secondTestCard;
    }

    public WebElement getAddToCartButtonElement() {
        return addToCartButton;
    }

    public WebElement getCartButtonElement() {
        return cartButton;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }
}