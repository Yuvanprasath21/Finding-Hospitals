package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.WellnessPlanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_17_InvalidWellness extends BaseTest {
    @Test
    public void invalidInputTest() {
        HomePage hp = new HomePage(driver);
        hp.clickWellness();
        hp.switchToNewTab();

        WellnessPlanPage wellnessPage = new WellnessPlanPage(driver);
        boolean isButtonEnabled = wellnessPage.invalidInput();

        Assert.assertFalse(isButtonEnabled,
                "Submit button should be ENABLED for valid inputs but was disabled.");

        System.out.println("Valid input test — Submit button enabled: " + isButtonEnabled);

    }
}