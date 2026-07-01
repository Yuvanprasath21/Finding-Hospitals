package org.hospital.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticsPage extends CommonCode {

    public DiagnosticsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href,'tests')]")
    private WebElement diagnosticsLink;


    public void clickDiagnostics() {
        clickByJS(diagnosticsLink);
    }


    @FindBy(xpath = "//div[contains(text(),'TOP CITIES')]")
    private WebElement topCitiesSection;

    public boolean isTopCitiesSectionDisplayed() {
        scrollIntoView(topCitiesSection);
        return topCitiesSection.isDisplayed();
    }


    @FindBy(xpath = "//div[normalize-space()='TOP CITIES']/following-sibling::ul/li//div[contains(@class,'o-f-color--primary')]")
    private List<WebElement> cityNames;

    public List<String> getTopCities() {

        List<String> cities = new ArrayList<>();

        for (WebElement city : cityNames) {
            cities.add(city.getText().trim());
        }

        return cities;
    }


}
