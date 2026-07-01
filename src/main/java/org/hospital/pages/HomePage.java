package org.hospital.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends CommonCode{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input")
    WebElement searchBox;

    @FindBy(xpath = "//input[@data-input-box-id='omni-searchbox-locality']")
    WebElement location;

    @FindBy(xpath = "//div[@data-qa-id='omni-suggestion-listing'][contains(.,'Hospital') and contains(.,'TYPE')]")
    WebElement suggestionValueOfHospital;

    @FindBy(xpath = "//div[@data-qa-id='omni-suggestion-main'][contains(.,'Bangalore')]")
    WebElement suggestionValueOfBangalore;

    public void findAndClickBanagalore() {
        location.click();
        location.sendKeys(Keys.CONTROL + "a");
        location.sendKeys(Keys.DELETE);

        location.sendKeys("Bangalore");

        wait.until(ExpectedConditions.visibilityOf(suggestionValueOfBangalore));

        suggestionValueOfBangalore.click();
    }

    public void findAndClickHospital(){
        searchBox.sendKeys("Hospital");
        wait.until(ExpectedConditions.elementToBeClickable(suggestionValueOfHospital)).click();
    }

}
