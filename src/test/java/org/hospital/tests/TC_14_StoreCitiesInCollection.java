package org.hospital.tests;

import java.util.List;

import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TC_14_StoreCitiesInCollection extends BaseTest {

    @Test
    public void verifyCityNamesStoredInCollection() {

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        diagnostics.clickDiagnostics();


        List<String> citiesCollection = diagnostics.getTopCities();


        Assert.assertNotNull(
                citiesCollection,
                "City collection is null"
        );

        Assert.assertFalse(
                citiesCollection.isEmpty(),
                "City collection is empty"
        );

        System.out.println("Cities stored in collection: " + citiesCollection);
        System.out.println("TC_14 - City names successfully stored in collection");
    }
}

