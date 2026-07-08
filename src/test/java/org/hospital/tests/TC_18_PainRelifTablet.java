package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.MedicinePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ScreenshotUtils;

import java.util.List;

public class TC_18_PainRelifTablet extends BaseTest {

    @Test
    public void collectPainReliefMedicines() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        HomePage hp = new HomePage(driver);
        logger.info("Clicking Medicines Report page link");
        hp.clickMedicinesReportPage();
        logger.info("Switching to Medicines Report tab");
        hp.switchToNewTab();
        logger.info("Initializing MedicinePage");
        MedicinePage medicinePage = new MedicinePage(driver);
        logger.info("Selecting Pain Relief category");
        medicinePage.clickPainRelief();
        logger.info("Collecting Pain Relief medicine names");
        List<String> medicines = medicinePage.getPainReliefMedicineNames();
        logger.info("Verifying medicine list is not null");
        logger.info("Taking Screenshot");
        ScreenshotUtils.takeScreenShot(driver,"TC_18_PainReliefMedicineList");
        Assert.assertNotNull(
                medicines,
                "Medicine list is NULL."
        );
        logger.info("Verifying medicine list is not empty");
        Assert.assertFalse(
                medicines.isEmpty(),
                "No medicines found under Pain relief."
        );
        logger.info("Total Pain Relief medicines found : {}", medicines.size());
        medicines.forEach(medicine ->
                logger.info("Medicine : {}", medicine));
        logger.info("Pain Relief medicines collection completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}