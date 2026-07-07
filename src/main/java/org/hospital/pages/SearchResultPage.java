package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchResultPage extends BasePage {

    private int scrollCount;

    private static final int TARGET_COUNT = 10;
    private static final int MAX_SCROLL_COUNT = 20;
    private static final double MIN_RATING = 3.5;
    private static final String BOOK_VISIT_BUTTON = ".//button[@class='c-book-cta']";
    private static final String OPEN_24_X_7 = "Open 24x7";
    private static final String HOSPITAL_LIST = "//li";
    private static final String HOSPITAL_NAME = ".//h2";
    private static final String HOSPITAL_RATING = ".//div[@class='c-feedback']/div/span[@class='u-bold']";

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='title']")
    WebElement title;

    public boolean isElementVisible(){
        return title.isDisplayed();
    }

    public String getTitle(){
        return title.getText();
    }

    public String countOfHospitals(){
        return getTitle()
                .split(" ")[0]
                .replace(",", "");
    }

    public Set<String> get24x7Hospitals(){
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(
                    By.xpath(HOSPITAL_LIST)
            );
            for (WebElement hospital : hospitals) {
                try {
                    if (hospital.getText().contains(OPEN_24_X_7)) {
                        String name = hospital.findElement(
                                By.xpath(HOSPITAL_NAME)
                        ).getText();
                        hospitalNames.add(name);
                        if (hospitalNames.size() == TARGET_COUNT)
                            break;
                    }
                } catch (Exception e) {
                    logger.warn("Skipping hospital: {}", e.getMessage());
                }
            }
            scrollToLastHospital();
            scrollCount++;
        }
        return hospitalNames;
    }

    public Set<String> getHighRatingHospitals() {
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(
                    By.xpath(HOSPITAL_LIST)
            );
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
                    logger.warn("Skipping (no rating shown): {}", name);
                }
                catch (NumberFormatException e) {
                    logger.warn("Skipping (rating not a number): {}", name);
                }
                catch (Exception e) {
                    logger.warn("Skipping (unexpected): {} : {}",
                            name,
                            e.getClass().getSimpleName());
                }
            }
            scrollToLastHospital();
            scrollCount++;
        }
        return hospitalNames;
    }

    public boolean areButtonsClickable() {
        Set<String> hospitalNames = new LinkedHashSet<>();
        scrollCount = 0;
        scrollToTop();
        while (hospitalNames.size() < TARGET_COUNT && scrollCount < MAX_SCROLL_COUNT) {
            List<WebElement> hospitals = driver.findElements(
                    By.xpath(HOSPITAL_LIST)
            );
            for (WebElement hospital : hospitals) {
                String name = null;
                try {
                    name = hospital.findElement(
                            By.xpath(HOSPITAL_NAME)
                    ).getText();
                    List<WebElement> buttons = hospital.findElements(
                            By.xpath(BOOK_VISIT_BUTTON)
                    );
                    if (buttons.isEmpty()) {
                        logger.warn("Skipping (no book button): {}", name);
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
                    logger.warn("Skipping (no name shown): {}", name);
                }
                catch (Exception e) {
                    logger.warn("Skipping (unexpected): {} : {}",
                                        name,
                                        e.getClass().getSimpleName()
                    );
                }
            }
            scrollToLastHospital();
            scrollCount++;
        }
        return hospitalNames.size() >= TARGET_COUNT;
    }

    private void scrollToLastHospital() {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                List<WebElement> hospitals = driver.findElements(By.xpath(HOSPITAL_LIST));
                if (!hospitals.isEmpty()) {
                    scrollIntoCenterView(hospitals.getLast());
                }
                return;
            }
            catch (StaleElementReferenceException e) {
                logger.warn("Scroll attempt {} failed", attempt);
            }
        }
    }

}
