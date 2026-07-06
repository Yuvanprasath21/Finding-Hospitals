package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_CallButtonClickable extends BaseTest {

    @Test
    public void verifyCallButtonsAreClickable() {
        HomePage hp = new HomePage(driver);
        hp.findAndClickCity("Bangalore");
        hp.findAndClickService("Hospital");

        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        boolean anyClickable = page.callButton();
        Assert.assertTrue(anyClickable, "No call button was clickable!");
    }
}