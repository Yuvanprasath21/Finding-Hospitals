package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public boolean validInput(){
        name.sendKeys("Yuvan");
        organizationname.sendKeys("CTS");
        contact.sendKeys("9876543210");
        email.sendKeys("hhfhgfh@gmail.com");
        Select select = new Select(size);
        select.selectByVisibleText("<500");

        Select select1 = new Select(interest);
        select1.selectByVisibleText("Referring someone");

        return button.isEnabled();
    }

    public boolean invalidInput(){
        name.sendKeys("yu");
        organizationname.sendKeys("tcs");
        contact.sendKeys("987654321");
        email.sendKeys("sdddsmhds");
        Select select = new Select(size);
        select.selectByIndex(2);

        Select select1 = new Select(interest);
        select1.selectByIndex(2);

        return button.isEnabled();
    }
}
