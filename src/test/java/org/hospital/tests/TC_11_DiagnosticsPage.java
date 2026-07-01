package org.hospital.tests;

import org.hospital.pages.DiagnosticsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TC_11_DiagnosticsPage extends BaseTest {

    @Test
    public void verifyUserCanNavigateToDiagnosticsPage() {

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        diagnostics.clickDiagnostics();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("tests"),"User did NOT navigate to Diagnostics page");

        System.out.println("TC_11 - User successfully navigated to Diagnostics page");
    }
}
