package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_15_InvalidPatientDetails extends BaseTest {

    @Test
    public void verifyInvalidPatientDetails() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);
        logger.info("Clicking Diagnostics menu");
        diagnostics.clickDiagnostics();
        Assert.assertTrue(
                diagnostics.isDiagnosticsPageOpened(),
                "Diagnostics page did not open"
        );
        logger.info("Selecting Bangalore city");
        diagnostics.selectBangalore();
        logger.info("Opening Fever Checkup package");
        diagnostics.clickFeverCard();
        Assert.assertTrue(
                diagnostics.isFeverCheckupPageOpened(),
                "Fever Checkup page did not open"
        );
        logger.info("Opening Patient Details page");
        diagnostics.clickBookButton();

        logger.info("Entering patient details");
        diagnostics.enterPatientName("John Doe");
        diagnostics.enterPatientAge("25");
        diagnostics.enterMobile("1234567890");
        diagnostics.enterEmail("john213gmail");
        diagnostics.triggerEmailValidation();
        Assert.assertTrue(
                diagnostics.areValidationMessagesDisplayed(),
                "Validation messages are not displayed"
        );

        String phoneError = diagnostics.getInvalidPhoneMessage();
        logger.debug("Phone Validation Message : {}", phoneError);
        Assert.assertEquals(
                phoneError,
                "Invalid Phone Number"
        );

        String emailError = diagnostics.getInvalidEmailMessage();
        logger.debug("Email Validation Message : {}", emailError);
        Assert.assertEquals(
                emailError,
                "Enter valid email"
        );
        logger.info("Invalid patient details validation verified successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}