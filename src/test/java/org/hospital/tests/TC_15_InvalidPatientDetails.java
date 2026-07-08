package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;
import utilities.ScreenshotUtils;

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
        String[] data = ExcelUtils.getPatientFormData();
        diagnostics.enterDetails(data);
        logger.info("Taking Screenshot");
        ScreenshotUtils.takeScreenShot(driver,"TC_15_InvalidDetails");
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