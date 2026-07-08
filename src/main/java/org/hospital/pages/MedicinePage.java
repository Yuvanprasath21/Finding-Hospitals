package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MedicinePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div/div[1]")
    WebElement painReliefCategory;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div/div[2]")
    WebElement heartHealthCategory;

    @FindBy(className = "m-product__title")
    List<WebElement> medicineNameElements;

    public MedicinePage(WebDriver driver) {
        super(driver);
    }

    public void clickPainRelief() {
        wait.until(ExpectedConditions.visibilityOf(painReliefCategory));
        scrollIntoCenterView(painReliefCategory);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(painReliefCategory)).click();
        } catch (Exception e) {
            clickByJS(painReliefCategory);
        }
    }

    public List<String> getPainReliefMedicineNames() {
        return collectMedicineNames();
    }

    public void clickHeartHealth() {
        wait.until(ExpectedConditions.visibilityOf(heartHealthCategory));
        scrollIntoCenterView(heartHealthCategory);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(heartHealthCategory)).click();
        } catch (Exception e) {
            clickByJS(heartHealthCategory);
        }
    }

    public List<String> getHeartHealthMedicineNames() {
        return collectMedicineNames();
    }

    private List<String> collectMedicineNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(medicineNameElements));
        List<String> medicineList = new ArrayList<>();
        for (WebElement el : medicineNameElements) {
            String name = el.getText().trim();
            if (!name.isEmpty()) {
                medicineList.add(name);
            }
        }
        return medicineList;
    }
}