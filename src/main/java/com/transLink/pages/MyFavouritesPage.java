package com.transLink.pages;

import com.transLink.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MyFavouritesPage {

    public static By favItem = By.xpath("//ul[@class='GTFSFavouritesList']//li//a");
    public static By pageTitle = By.id("my-favourites");

    public static void  checkFavItem(String expVal){
        BrowserUtils.waitForVisibility(BrowserUtils.getElement(pageTitle),3000);
        Assert.assertEquals(BrowserUtils.getElement(favItem).getText(), expVal);
    }

}
