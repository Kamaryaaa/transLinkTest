package com.transLink.pages;

import com.transLink.utils.BrowserUtils;
import com.transLink.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UBC_B_LinePage {

    public static By startDate = By.xpath("//input[contains(@id,'startdate')]");

    public static By startTime = By.xpath("//input[contains(@id,'starttime')]");

    public static By endTime = By.xpath("//input[contains(@id,'endtime')]");

    public static By scheduleSearchBtn = By.xpath("//button[contains(@aria-controls,'DesktopSchedulesTable')]");

    public static By selectedStopOnlyBtn = By.xpath("//button[contains(@name,'ShowSelectedStops')]");

    public static By addFavouredBtn = By.xpath("//div[@class='showWhenNotExpanded']//..");

    public static By addFavouredBtnInPopup = By.xpath("//*[@id='add-to-favourites']//..//..//section//button");

    public static By favoriteInputBox = By.id("newfavourite");

    public static By filterStopsDropdown = By.xpath("//button[contains(@aria-controls,'StopsPicker-listbox')]");

    public static By filterStopsList = By.xpath("//ul[contains(@id,'StopsPicker-listbox')]");

    public static By manageFavBtn = By.linkText("Manage my favourites");

    public static By table = By.xpath("//table[@id='DesktopSchedulesTable']");

    public static By listTime = By.xpath("//*[@aria-label='#58613 - E Broadway at Fraser St']//..//..//td");

    /**
     * update date and time
     */
    public static void updateScheduleTime() {

        LocalDate date = LocalDate.now();

        // convert time to string
        String tomorrow = date.plusDays(1).toString().substring(4);

        // update Date
        BrowserUtils.getElement(startDate).sendKeys(tomorrow);

        // update time
        BrowserUtils.getElement(startTime).sendKeys("07:00");
        BrowserUtils.getElement(endTime).sendKeys("08:30 a");

        // click search btn
        BrowserUtils.getElement(scheduleSearchBtn).click();
    }

    /**
     * Select items from filter stops dropdown
     */
    public static void selectStopsFromFilterDropdown(String... ids) {
        // click dropdown
        BrowserUtils.getElement(filterStopsDropdown).click();
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(filterStopsList), 5);
        // check items
        selectCheckBoxByID(ids);

        // click selected stop only btn
        BrowserUtils.getElement(selectedStopOnlyBtn).click();
    }

    /**
     * get time list from stop time table
     */
    public static void getTimeListAndCompare() throws ParseException {
        BrowserUtils.scrollIntoMiddleView(BrowserUtils.getElement(table));
        List<WebElement> timeList = Driver.getDriver().findElements(listTime).stream().filter(
                el -> el.getText().length() >= 1
        ).collect(Collectors.toList()).subList(1, 5); // get first four timeStemp

        // validate time steps in Ascending order and time period within 30 minutes
        for (int i = 0; i < timeList.size() - 1; i++) {

            Assert.assertTrue(checkTimeIsInAscOrderAndTimePeriodIn30min(timeList.get(i).getText(),
                    timeList.get(i + 1).getText()));
        }
    }

    /**
     * check time is as ascending order and time period within 30 minutes
     *
     * @throws ParseException
     */
    public static boolean checkTimeIsInAscOrderAndTimePeriodIn30min(String time1, String time2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date d1 = sdf.parse(time1);
        Date d2 = sdf.parse(time2);
        long elapsed = d2.getTime() - d1.getTime();
        //TO check the order and its time-stamps is within 30 minutes
        return elapsed > 0 && elapsed <= 1800000;
    }

    /**
     * select targeted 4 item from dropdown
     */
    public static void selectCheckBoxByID(String... args) {
        for (String arg : args) {
            Driver.getDriver().findElement(By.xpath("//input[@value=" + arg + "]")).click();
        }

    }

    /**
     * Click add to favorite btn
     */
    public static void clickAddToFavorite() {
        BrowserUtils.getElement(addFavouredBtn).click();
    }

    /**
     * rename new favorite list from popup
     */
    public static void renameFavorite(String newValue) {
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(favoriteInputBox), 5000);
        BrowserUtils.getElement(favoriteInputBox).clear();

        BrowserUtils.getElement(favoriteInputBox).sendKeys(newValue);
    }

    /**
     * Click add to favorite btn in popup and click manage btn
     */
    public static void clickAddToFavoriteInPopupAndClickManageFavBtn() {
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(addFavouredBtnInPopup), 5000);
        BrowserUtils.getElement(addFavouredBtnInPopup).click();
        // click manage favorite btn
        BrowserUtils.getElement(manageFavBtn).click();
    }

}
