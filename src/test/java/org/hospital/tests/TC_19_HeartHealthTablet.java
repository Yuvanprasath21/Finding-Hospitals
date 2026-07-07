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
        HomePage hp = new HomePage(driver);
        hp.clickMedicinesReportPage();
        hp.switchToNewTab();

        MedicinePage medicinePage = new MedicinePage(driver);
        medicinePage.clickHeartHealth();

        List<String> medicines = medicinePage.getHeartHealthMedicineNames();

        Assert.assertNotNull(medicines, "Medicine list is NULL.");
        Assert.assertFalse(medicines.isEmpty(), "No medicines found under Heart health.");

        System.out.println("Total medicines found: " + medicines.size());
        medicines.forEach(System.out::println);
    }
}