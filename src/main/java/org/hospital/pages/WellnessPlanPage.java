package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WellnessPlanPage extends BasePage {

    @FindBy(id = "name")
    WebElement name;

    @FindBy(id = "organizationName")
    WebElement organizationname;

    @FindBy(id = "contactNumber")
    WebElement contact;

    @FindBy(id = "officialEmailId")
    WebElement email;

    @FindBy(id = "organizationSize")
    WebElement size;

    @FindBy(id = "interestedIn")
    WebElement interest;

    @FindBy(xpath = "/html/body/div[1]/div/div/header[1]/div[2]/div/form/button")
    WebElement button;

    public WellnessPlanPage(WebDriver driver) {
        super(driver);
    }

    public boolean enterDetails(String[] data) {
        name.sendKeys(data[0]);
        organizationname.sendKeys(data[1]);
        contact.sendKeys(data[2]);
        email.sendKeys(data[3]);
        new Select(size)
                .selectByIndex(Integer.parseInt(data[4]));
        new Select(interest)
                .selectByIndex(Integer.parseInt(data[5]));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(button));
        } catch (Exception e) {
            // ignore timeout
        }
        return button.isEnabled();
    }
}
