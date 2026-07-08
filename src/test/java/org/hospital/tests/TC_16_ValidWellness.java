package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.WellnessPlanPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;
import utilities.ScreenshotUtils;

public class TC_16_ValidWellness extends BaseTest {

    @Test
    public void validInputTest() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        HomePage hp = new HomePage(driver);
        logger.info("Clicking Wellness Plans link");
        hp.clickWellness();
        logger.info("Switching to Wellness Plans tab");
        hp.switchToNewTab();
        logger.info("Initializing WellnessPlanPage");
        WellnessPlanPage wellnessPage = new WellnessPlanPage(driver);
        logger.info("Entering valid wellness plan details");
        String[] data = ExcelUtils.getWellnessFormData(0);
        boolean isButtonEnabled = wellnessPage.enterDetails(data);
        logger.info("Submit button enabled status : {}", isButtonEnabled);
        logger.info("Verifying submit button is enabled");
        logger.info("Taking Screenshot");
        ScreenshotUtils.takeScreenShot(driver,"TC_16_ValidWellnessForm");
        Assert.assertTrue(
                isButtonEnabled,
                "Submit button should be ENABLED for valid inputs but was disabled."
        );
        logger.info("Valid wellness plan test completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}