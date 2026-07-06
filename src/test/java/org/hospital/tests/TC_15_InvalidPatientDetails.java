package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_15_InvalidPatientDetails extends BaseTest {

    @Test
    public void verifyInvalidPatientDetails() throws InterruptedException {
        driver.get(properties.getProperty("website_url"));

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);
        diagnostics.clickDiagnostics();

        wait.until(ExpectedConditions.urlContains("tests"));
        diagnostics.selectBangalore();

        wait.until(ExpectedConditions.visibilityOf(diagnostics.getFeverCardElement()));
        diagnostics.clickFeverCard();

        wait.until(ExpectedConditions.urlContains("fever-checkup"));
        System.out.println("Fever checkup page: " + driver.getCurrentUrl());

        diagnostics.clickBookButton();

        diagnostics.enterPatientName("John Doe");
        diagnostics.enterPatientAge("25");
        diagnostics.enterMobile("1234567890");         // invalid phone
        diagnostics.enterEmail("john213gmail");        // invalid email
        diagnostics.getEmailElement().sendKeys(Keys.TAB);

        wait.until(ExpectedConditions.visibilityOf(diagnostics.getInvalidPhoneMessageElement()));
        wait.until(ExpectedConditions.visibilityOf(diagnostics.getInvalidEmailMessageElement()));

        String phoneError = diagnostics.getInvalidPhoneMessage();
        String emailError = diagnostics.getInvalidEmailMessage();

        System.out.println("Phone Validation Message : " + phoneError);
        System.out.println("Email Validation Message : " + emailError);

        Assert.assertEquals(phoneError, "Invalid Phone Number");
        Assert.assertEquals(emailError, "Enter valid email");

    }
}