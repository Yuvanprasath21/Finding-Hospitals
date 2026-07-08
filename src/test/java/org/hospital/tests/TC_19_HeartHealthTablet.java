package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.MedicinePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_19_HeartHealthTablet extends BaseTest {

    @Test
    public void collectHeartHealthMedicines() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        HomePage hp = new HomePage(driver);
        logger.info("Clicking Medicines Report page link");
        hp.clickMedicinesReportPage();
        logger.info("Switching to Medicines Report tab");
        hp.switchToNewTab();
        logger.info("Initializing MedicinePage");
        MedicinePage medicinePage = new MedicinePage(driver);
        logger.info("Selecting Heart Health category");
        medicinePage.clickHeartHealth();
        logger.info("Collecting Heart Health medicine names");
        List<String> medicines = medicinePage.getHeartHealthMedicineNames();
        logger.info("Verifying medicine list is not null");
        Assert.assertNotNull(
                medicines,
                "Medicine list is NULL."
        );
        logger.info("Verifying medicine list is not empty");
        Assert.assertFalse(
                medicines.isEmpty(),
                "No medicines found under Heart health."
        );
        logger.info("Total Heart Health medicines found : {}", medicines.size());
        medicines.forEach(medicine ->
                logger.info("Medicine : {}", medicine));
        logger.info("Heart Health medicines collection completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}