package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_20_SocialMediaLInks extends BaseTest {
    @Test
    public void linkVerification() {

        logger.info("Starting : {}", this.getClass().getSimpleName());

        HomePage homePage = new HomePage(driver);
        logger.info("Verifying Facebook Link");
        Assert.assertTrue(
                homePage.verifyFacebookLink(),
                "Facebook link validation failed"
        );
        logger.info("Verifying Twitter Link");
        Assert.assertTrue(
                homePage.verifyTwitterLink(),
                "Twitter link validation failed"
        );
        logger.info("Verifying LinkedIn Link");
        Assert.assertTrue(
                homePage.verifyLinkedInLink(),
                "LinkedIn link validation failed"
        );
        logger.info("Verifying YouTube Link");
        Assert.assertTrue(
                homePage.verifyYoutubeLink(),
                "YouTube link validation failed"
        );
        logger.info("Verifying GitHub Link");
        Assert.assertTrue(
                homePage.verifyGithubLink(),
                "GitHub link validation failed"
        );
        logger.info("All social media links validated successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}
