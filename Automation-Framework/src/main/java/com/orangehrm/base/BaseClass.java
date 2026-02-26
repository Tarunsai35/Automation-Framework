package com.orangehrm.base;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    protected WebDriver driver;

    @BeforeMethod
    public void setupBrowser() throws IOException {
        String browser = ConfigReader.getProperty("browser");
        String headlessString = ConfigReader.getProperty("headless");
        boolean headless = Boolean.parseBoolean(headlessString); // Convert to boolean
        
        if (browser == null) {
            throw new IllegalArgumentException("Browser not specified in config.");
        }

        // Initialize browser based on config
        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
            }

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            driver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Set implicit wait time from config
        String implicitWaitStr = ConfigReader.getProperty("implicitWait");
        int implicitWait = implicitWaitStr != null ? Integer.parseInt(implicitWaitStr) : 30; // default to 30 if not set
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        driver.manage().window().maximize();

        // Get the URL to navigate to
        String url = ConfigReader.getProperty("url");
        if (url == null) {
            throw new IllegalArgumentException("URL not specified in config.");
        }
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser after test method
        }
    }

}