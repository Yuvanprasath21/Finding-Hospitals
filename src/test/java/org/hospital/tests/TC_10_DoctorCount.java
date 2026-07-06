package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_DoctorCount extends BaseTest {

    @Test
    public void verifyDoctorCountMatches() {
        HomePage hp = new HomePage(driver);
        hp.findAndClickCity("Bangalore");
        hp.findAndClickService("Hospital");

        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        boolean result = page.checkingDoctor(3);
        Assert.assertTrue(result, "Doctor count mismatch for one or more hospitals!");
    }
}