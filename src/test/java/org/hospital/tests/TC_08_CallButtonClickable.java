package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_CallButtonClickable extends BaseTest {

    @Test
    public void verifyCallButtonsAreClickable() {
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        boolean anyClickable = page.callButton();
        Assert.assertTrue(anyClickable, "No call button was clickable!");
    }
}