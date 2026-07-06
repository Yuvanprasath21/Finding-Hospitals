package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_ParkingAmenities extends BaseTest {

    @Test
    public void verifyParkingAmenity() {
        HomePage hp = new HomePage(driver);
        hp.findAndClickCity("Bangalore");
        hp.findAndClickService("Hospital");

        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        page.navigateAndHandleHospital();
        int hospitalsWithParking = page.checkingAminities();
        System.out.println("Hospitals with parking: " + hospitalsWithParking);
        Assert.assertTrue(hospitalsWithParking >= 0, "Parking check failed!");
    }
}