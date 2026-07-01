package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.testng.annotations.Test;

@Test
public class TC_01_BangaloreHospitals extends BaseTest {
    public void getHospitals() {
        HomePage homePage =new HomePage(driver);

        homePage.findAndClickBanagalore();
        homePage.findAndClickHospital();
    }
}
