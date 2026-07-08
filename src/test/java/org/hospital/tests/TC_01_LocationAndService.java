package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ScreenshotUtils;

import java.io.IOException;

@Test
public class TC_01_LocationAndService extends BaseTest {
    public void getHospitals() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Starting Location and Service validation");

        String websiteUrl = ConfigReader.getProperty("website_url");
        logger.info("Verifying website URL");
        Assert.assertEquals(
                driver.getCurrentUrl(),
                websiteUrl,
                "WebsiteURL is wrong"
        );
        logger.info("Website URL verified successfully");

        HomePage homePage =new HomePage(driver);
        String city = ConfigReader.getProperty("city");
        logger.info("Selecting city: {}", city);
        homePage.findAndClickCity(city);
        String service = ConfigReader.getProperty("service");
        logger.info("Selecting service: {}", service);
        homePage.findAndClickService(service);

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        String title = searchResultPage.getTitle().toLowerCase();
        logger.info("Verifying search results page");
        Assert.assertTrue(
                title.contains(city) && title.contains(service)
                ,"Page is not loaded correctly for location and service"
        );
        logger.info("Taking Screenshot");
        ScreenshotUtils.takeScreenShot(driver,"TC_01_LocationAndService");
        logger.info("Location and Service validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}
