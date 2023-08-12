package com.transLink.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

     /*
     Use singleton pattern
     Share same object across the all class of the application
     */

    private Driver() {
    }

    private static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            // Get the browser type from the "configuration.properties" files
            String browser = ConfigurationReader.getProperty("browser");
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("chrome-headless")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("firefox-headless")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
            } else if (browser.equalsIgnoreCase("edge")) {
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    throw new WebDriverException("Your operating system does not support the requested browser");
                }
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("safari")) {
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    throw new WebDriverException("Your operating system does not support the requested browser");
                }
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver = new SafariDriver();
            }

        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
