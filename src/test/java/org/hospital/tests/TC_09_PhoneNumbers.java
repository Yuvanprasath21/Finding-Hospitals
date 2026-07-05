package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09_PhoneNumbers extends BaseTest {

    @Test
    public void capturePhoneNumbers() {
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        page.callButton(); // reveal numbers first
        List<String> phones = page.getPhoneNumbers();
        System.out.println("Captured phone numbers: " + phones);
        Assert.assertFalse(phones.isEmpty(), "No phone numbers captured!");
    }
}