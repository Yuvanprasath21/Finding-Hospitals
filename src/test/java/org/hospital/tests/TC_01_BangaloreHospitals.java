package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TC_01_BangaloreHospitals extends BaseTest {
    public void getHospitals() {
        HomePage homePage =new HomePage(driver);
        String websiteUrl = properties.getProperty("website_url");
        Assert.assertEquals(
                driver.getCurrentUrl(),
                websiteUrl
        );
        String city = properties.getProperty("city");
        homePage.findAndClickCity(city);
        String service = properties.getProperty("service");
        homePage.findAndClickService(service);

        // add assertion to check if page loaded for respective location and service

    }
}
