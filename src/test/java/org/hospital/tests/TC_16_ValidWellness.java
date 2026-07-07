package org.hospital.tests;


import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.WellnessPlanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_16_ValidWellness extends BaseTest{
    @Test
    public void validInputTest(){
        HomePage hp = new HomePage(driver);
        hp.clickWellness();
        hp.switchToNewTab();

        WellnessPlanPage wellnessPage = new WellnessPlanPage(driver);
        boolean isButtonEnabled = wellnessPage.validInput();

        Assert.assertTrue(isButtonEnabled,
                "Submit button should be ENABLED for valid inputs but was disabled.");

        System.out.println("Valid input test — Submit button enabled: " + isButtonEnabled);


    }
}
