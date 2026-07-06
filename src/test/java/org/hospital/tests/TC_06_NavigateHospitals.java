package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_NavigateHospitals extends BaseTest {

    @Test
    public void navigateHospitalsInNewTabs() {
        HomePage hp = new HomePage(driver);
        hp.findAndClickCity("Bangalore");
        hp.findAndClickService("Hospital");

        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        int totalWindows = page.navigateAndHandleHospital();
        System.out.println("Total windows opened: " + totalWindows);
        Assert.assertTrue(totalWindows > 1, "No new tabs opened!");
    }
}