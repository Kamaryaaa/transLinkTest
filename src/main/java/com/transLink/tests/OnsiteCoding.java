package com.transLink.tests;

import com.transLink.pages.MainPage;
import com.transLink.utils.BrowserUtils;
import com.transLink.utils.ConfigurationReader;
import com.transLink.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OnsiteCoding {




    @BeforeClass
    public void setUp() {
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
    }

    @Test
    public void test3() throws ParseException {

        // 1) Open the url of the page
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        WebElement buzzerBlog_asWebElement = BrowserUtils.getElement(MainPage.buzzerBlogFeature);

        BrowserUtils.scrollIntoMiddleView(buzzerBlog_asWebElement);
        BrowserUtils.waitForVisibility(buzzerBlog_asWebElement,5);

        List< WebElement> timeList = Driver.getDriver().findElements(By.xpath("(//section[@class='CopyMain'])//ul[@class='spacedList menu']//time"));

        List <Date> dates = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



        for(WebElement eachTime : timeList){

            String dataText = eachTime.getAttribute("datetime");
            Date date = dateFormat.parse(dataText);
            dates.add(date);
        }
        boolean isDescending= true;
        for (int i = 1; i < dates.size(); i++) {
            if(dates.get(i).compareTo(dates.get(i-1)) > 0) {
                isDescending=false;
                break;
            }

        }
        if(isDescending){
            System.out.println("Dates are in descending order");
        }else {
            System.out.println("dates are not in descending order");
        }







    }

    @AfterClass
    public void closeDriver() {
        Driver.getDriver().close();
    }






}
