package org.hospital.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DoctorsLoginPage extends CommonCode {

    public DoctorsLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Mobile Number / Email ID']")
    WebElement email;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement rememberMe;

    public void enterEmail(String mail) {
        email.clear();
        email.sendKeys(mail);
    }

    public void enterPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public boolean isLoginButtonDisplayed() {
        return loginBtn.isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        return loginBtn.isEnabled();
    }
}