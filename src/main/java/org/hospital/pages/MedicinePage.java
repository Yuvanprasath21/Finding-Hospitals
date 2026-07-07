package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MedicinePage extends BasePage {

    // ---------- Pain relief ----------
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div/div[1]")
    WebElement painReliefCategory;

    // ---------- Heart health ----------
    // Sits right next to Pain relief in the same category row → likely div[2] under the same parent
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[4]/div/div[2]/div[2]/div[1]/div/div[2]")
    WebElement heartHealthCategory;

    // ---------- Medicine tiles ----------
    @FindBy(className = "m-product__title")
    private List<WebElement> medicineNameElements;

    public MedicinePage(WebDriver driver) {
        super(driver);
    }

    // ===================== Pain relief =====================

    public void clickPainRelief() {
        wait.until(ExpectedConditions.visibilityOf(painReliefCategory));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                painReliefCategory);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(painReliefCategory)).click();
        } catch (Exception e) {
            clickByJS(painReliefCategory);
        }
    }

    public List<String> getPainReliefMedicineNames() {
        return collectMedicineNames();
    }

    // ===================== Heart health =====================

    public void clickHeartHealth() {
        wait.until(ExpectedConditions.visibilityOf(heartHealthCategory));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                heartHealthCategory);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(heartHealthCategory)).click();
        } catch (Exception e) {
            clickByJS(heartHealthCategory);
        }
    }

    public List<String> getHeartHealthMedicineNames() {
        return collectMedicineNames();
    }

    // ===================== Reusable helper =====================

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