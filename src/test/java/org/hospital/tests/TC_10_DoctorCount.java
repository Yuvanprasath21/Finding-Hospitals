package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_DoctorCount extends BaseTest {

    @Test
    public void verifyDoctorCountMatches() {
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        int expectedHospitals = Integer.parseInt(config.getProperty("expected.hospitals"));
        boolean result = page.checkingDoctor(expectedHospitals);
        Assert.assertTrue(result, "Doctor count mismatch for one or more hospitals!");
    }
}