package com.transLink.pages;

import com.transLink.utils.BrowserUtils;
import org.openqa.selenium.By;

public class MainPage {

    public static By scheduleMapLink = By.linkText("Schedules and Maps");
    public static By buzzerBlogFeature = By.xpath("//h3[text()='Buzzer Blog']");

    /**
     * Select schedules and maps link and click Bus option
     */
    public static void hoverOverToScheduleAndMapThenClickByText(String text) {
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(scheduleMapLink), 5);
        BrowserUtils.hoverOverToElement(BrowserUtils.getElement(scheduleMapLink));

        // click Bus option
        clickByLinkText(text);
    }

    /**
     * Click element by link text
     */
    public static void clickByLinkText(String linkText) {
        //   Driver.getDriver().findElement(By.linkText(linkText)).click();
        BrowserUtils.getElement(By.linkText(linkText)).click();
    }


}
