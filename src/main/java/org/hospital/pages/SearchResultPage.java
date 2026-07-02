package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchResultPage extends BasePage {

    private int scrollCount;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='title']")
    WebElement title;

    public boolean isElementVisible(){
        return title.isDisplayed();
    }

    public String countOfHospitals(){
        return title.getText()
                .split(" ")[0]
                .replace(",", "");
    }

    public Set<String> get24x7Hospitals(){
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(By.xpath(HOSPITAL_LIST));
            for (WebElement hospital : hospitals) {
                try {
                    if (hospital.getText().contains(OPEN_24_X_7)) {
                        String name = hospital.findElement(By.xpath(HOSPITAL_NAME)).getText();
                        hospitalNames.add(name);
                        if (hospitalNames.size() == TARGET_COUNT)
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Skipping hospital:\n" + e.getMessage());
                }
            }
            WebElement lastHospital = hospitals.getLast();
            scrollIntoView(lastHospital);
            scrollCount++;
        }
        return hospitalNames;
    }

    public Set<String> getHighRatingHospitals() {
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(By.xpath(HOSPITAL_LIST));
            for (WebElement hospital : hospitals) {
                String name=null;
                try {
                    name = hospital.findElement(
                                    By.xpath(HOSPITAL_NAME))
                            .getText();
                    double rating = Double.parseDouble(
                            hospital.findElement(By.xpath(HOSPITAL_RATING))
                                    .getText());
                    if (rating > MIN_RATING) {
                        hospitalNames.add(name);
                        if (hospitalNames.size() == TARGET_COUNT) {
                            break;
                        }
                    }
                }
                catch (NoSuchElementException e) {
                    System.out.println("Skipping (no rating shown): " + name);
                }
                catch (NumberFormatException e) {
                    System.out.println("Skipping (rating not a number): " + name);
                }
                catch (Exception e) {
                    System.out.println("Skipping (unexpected): " + name + " → " + e.getClass().getSimpleName());
                }
            }
            WebElement lastHospital = hospitals.getLast();
            scrollIntoView(lastHospital);
            scrollCount++;
        }
        return hospitalNames;
    }

    public boolean areButtonsClickable() {
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(By.xpath(HOSPITAL_LIST));
            for (WebElement hospital : hospitals) {
                String name = null;
                try {
                    name = hospital.findElement(By.xpath(HOSPITAL_NAME)).getText();
                    List<WebElement> buttons = hospital.findElements(By.xpath(BOOK_VISIT_BUTTON));
                    if (buttons.isEmpty()) {
                        System.out.println("Skipping (no book button): " + name);
                        continue;
                    }
                    WebElement button = buttons.getFirst();
                    if (!(button.isDisplayed() && button.isEnabled())) {
                        return false;
                    }
                    hospitalNames.add(name);
                    if (hospitalNames.size() == TARGET_COUNT)
                        break;
                }
                catch (NoSuchElementException e) {
                    System.out.println("Skipping (no name shown): " + name);
                }
                catch (Exception e) {
                    System.out.println("Skipping (unexpected): " + name + " → " + e.getClass().getSimpleName());
                }
            }
            WebElement lastHospital = hospitals.getLast();
            scrollIntoView(lastHospital);
            scrollCount++;
        }
        return hospitalNames.size() >= TARGET_COUNT;
    }
}
