package com.transLink.tests;

import com.transLink.pages.BusSchedulePage;
import com.transLink.pages.MainPage;
import com.transLink.pages.MyFavouritesPage;
import com.transLink.pages.UBC_B_LinePage;
import com.transLink.utils.BrowserUtils;
import com.transLink.utils.ConfigurationReader;
import com.transLink.utils.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class TransLinkTest {

    static final String busSchedulePageTitle = "Bus Schedules";
    static final String busOption = "Bus";
    static final String newName = "99 UBC B-Line – Morning Schedule";

    @BeforeClass
    public void setUp() {
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
    }

    @Test
    public void test1() throws ParseException {
        // 1) Open the url of the page
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        // 2) Expand the top menu, select “Schedules and Maps &gt; Bus”
        MainPage.hoverOverToScheduleAndMapThenClickByText(busOption);

        // 3) Validate the page is loaded
        Assert.assertTrue(BusSchedulePage.getPageTitle().contains(busSchedulePageTitle));

        //  4,5) Input “99” and click “Find Schedule” button
        BusSchedulePage.inputValueAndFindSchedule("99");

        // 6) In the result list, select and click “#99 – UBC B-Line”
        BusSchedulePage.clickFirstResultLink();

        // 7) In the bus schedule page of “99”, update date and time and click Search
        UBC_B_LinePage.updateScheduleTime();

        // 8) In the schedule list, pick up following stops and click selected stops only btn
        UBC_B_LinePage.selectStopsFromFilterDropdown("50913", "58613", "50916");

        // Test Scenario for bonus points,
        //   1) From the scenario above Step 8) and click on “Selected Stop Only”
        //   2) Write the code to validate that the first 4 timestamps of stop #58613 are listed in order and the time interval between two buses is less than 30 minutes.
        UBC_B_LinePage.getTimeListAndCompare();
    }

    @Test
    public void test2() {

        // 9) Click on “Add to favourites” button on the right side
        UBC_B_LinePage.clickAddToFavorite();

        // 10) Rename it to be “99 UBC B-Line – Morning Schedule”
        UBC_B_LinePage.renameFavorite(newName);

        // 11) Click on “Add to favourites” and click on “Manage my favourites” button on the right side
        UBC_B_LinePage.clickAddToFavoriteInPopupAndClickManageFavBtn();

        // 12) validate the item just added.
        MyFavouritesPage.checkFavItem(newName);

    }



    @AfterClass
    public void closeDriver() {
        Driver.getDriver().close();
    }

}



