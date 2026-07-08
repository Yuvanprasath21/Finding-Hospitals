package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.WellnessPlanPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

public class TC_17_InvalidWellness extends BaseTest {

    @Test
    public void invalidInputTest() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        HomePage hp = new HomePage(driver);
        logger.info("Clicking Wellness Plans link");
        hp.clickWellness();
        logger.info("Switching to Wellness Plans tab");
        hp.switchToNewTab();
        logger.info("Initializing WellnessPlanPage");
        WellnessPlanPage wellnessPage = new WellnessPlanPage(driver);
        logger.info("Entering invalid wellness plan details");
        String[] data = ExcelUtils.getFormData(1);
        boolean isButtonEnabled = wellnessPage.enterDetails(data);
        logger.info("Submit button enabled status : {}", isButtonEnabled);
        logger.info("Verifying submit button is disabled for invalid inputs");
        Assert.assertFalse(
                isButtonEnabled,
                "Submit button should be DISABLED for invalid inputs but was enabled."
        );
        logger.info("Invalid wellness plan validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}