package com.transLink.pages;

import com.transLink.utils.BrowserUtils;
import com.transLink.utils.Driver;
import org.openqa.selenium.By;

public class BusSchedulePage {

    public static By pageTitle = By.id("bus-schedules");

    public static By searchInputBox = By.xpath("//input[contains(@id,'find-schedule-searchbox')]");

    public static By findScheduleBtn = By.xpath("//span[.='Find Schedule']//..");

    public static By first_searchingResult = By.xpath("//*[contains(@href,'1/schedule')]");

    /**
     * Get page title
     *
     * @return
     */
    public static String getPageTitle() {
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(pageTitle), 5);

        return Driver.getDriver().getTitle();
    }

    /**
     * Input value on searchBox and click find schedule btn
     */
    public static void inputValueAndFindSchedule(String inputValue) {
        // input value
        BrowserUtils.getElement(searchInputBox).sendKeys(inputValue);
        // click find schedule btn
        BrowserUtils.getElement(findScheduleBtn).click();
    }

    /**
     * Click first result from result list
     */
    public static void clickFirstResultLink() {
        BrowserUtils.getElement(first_searchingResult).click();
    }


}
