package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.MedicinePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_18_PainRelifTablet extends BaseTest{
    @Test
    public void collectPainReliefMedicines() {
        HomePage hp = new HomePage(driver);
        hp.clickMedicinesReportPage();
        hp.switchToNewTab();

        MedicinePage medicinePage = new MedicinePage(driver);
        medicinePage.clickPainRelief();
        List<String> medicines = medicinePage.getPainReliefMedicineNames();

        Assert.assertNotNull(medicines, "Medicine list is NULL.");
        Assert.assertFalse(medicines.isEmpty(), "No medicines found under Pain relief.");

        System.out.println("Total medicines found: " + medicines.size());
        medicines.forEach(System.out::println);
    }
}
