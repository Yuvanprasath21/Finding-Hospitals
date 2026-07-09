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
        try {
            wait.until(ExpectedConditions.visibilityOf(name));
            name.sendKeys(data[0]);
            wait.until(ExpectedConditions.visibilityOf(organizationname));
            organizationname.sendKeys(data[1]);
            wait.until(ExpectedConditions.visibilityOf(contact));
            contact.sendKeys(data[2]);
            wait.until(ExpectedConditions.visibilityOf(email));
            email.sendKeys(data[3]);
            wait.until(ExpectedConditions.visibilityOf(size));
            new Select(size)
                    .selectByIndex(Integer.parseInt(data[4]));
            wait.until(ExpectedConditions.visibilityOf(interest));
            new Select(interest)
                .selectByIndex(Integer.parseInt(data[5]));
            try {
                scrollIntoCenterView(button);
                wait.until(ExpectedConditions.elementToBeClickable(button));
            }
            catch(Exception exception1){
                //pass
            }
        } catch (Exception exception2) {
            logger.warn(
                    "Wellness Plan form submit button validation could not be completed due to reason: {}",
                    exception2.getMessage()
            );
        }
        return button.isEnabled();
    }
}
