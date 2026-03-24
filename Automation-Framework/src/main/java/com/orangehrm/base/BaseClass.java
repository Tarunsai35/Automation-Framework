package com.orangehrm.base;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.ExtendManager;
import com.orangehrm.utilities.LoggerManager;

public class BaseClass {

//	protected WebDriver driver;
//	private static ActionDriver actionDriver;
//	protected ConfigReader config;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<ActionDriver>();

	public static final Logger logger = LoggerManager.getlogger(BaseClass.class);
	
	protected ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);
	
	//Getter method for soft assert
	public SoftAssert getSoftAssert() {
		return softAssert.get();
	}

	// constructor
	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver = driver;
	}

	@BeforeMethod
	public void setupBrowser() throws IOException {

		logger.info("========== TEST STARTED : {} ==========", this.getClass().getSimpleName());

		String browser = ConfigReader.getProperty("browser");
		String headlessString = ConfigReader.getProperty("headless");
		boolean headless = Boolean.parseBoolean(headlessString); // Convert to boolean

		if (browser == null) {
			throw new IllegalArgumentException("Browser not specified in config.");
		}

		// Initialize browser based on ConfigReader
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();

			if (headless) {
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				logger.info("Headless mode Initialized - chrome");
			}

//			driver = new ChromeDriver(options);
			driver.set(new ChromeDriver());
			ExtendManager.registerDriver(getDriver());
			logger.info("ChromeDriver Initialized");

		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();

			if (headless) {
				options.addArguments("-headless");
				logger.info("Headless mode Initialized - firefox");
			}

//			driver = new FirefoxDriver(options);
			driver.set(new FirefoxDriver());
			ExtendManager.registerDriver(getDriver());
			logger.info("FirefoxDriver Initialized");
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		// Set implicit wait time from ConfigReader
		String implicitWaitStr = ConfigReader.getProperty("implicitWait");
		int implicitWait = implicitWaitStr != null ? Integer.parseInt(implicitWaitStr) : 30; // default to 30 if not set
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the browser
		getDriver().manage().window().maximize();

		// Get the URL from ConfigReader
		String url = ConfigReader.getProperty("url");
		if (url == null) {
			throw new IllegalArgumentException("URL not specified in config.");
		}

		// Navigate to url
		getDriver().get(url);

		// Initialize ActionDriver
		/*
		 * if(actionDriver == null) { actionDriver = new ActionDriver(driver);
		 * logger.info("Actiondriver insatnce is created -->"+Thread.currentThread().
		 * getId()); }
		 */

		actionDriver.set(new ActionDriver(getDriver()));
		logger.info("ActionDriver initlalized for thread: -->" + Thread.currentThread().getId());
	}

	@AfterMethod
	public void tearDown() {
		if (driver.get() != null) {
			try {
				getDriver().quit();
			} catch (Exception e) {
				logger.error("unable to quit the driver: " + e.getMessage());
			}
		}

		logger.info("Close down Webdriver Instance");
		logger.info("Close down Actiondriver Instance");
		logger.info("========== TEST END : {} ==========", this.getClass().getSimpleName());

		driver.remove();
		actionDriver.remove();
//		driver = null;
//		actionDriver = null;
//		ExtendManager.endTest(); --This has been implemented in testListener

	}

	// Getter method for driver
	public static WebDriver getDriver() {

		if (driver.get() == null) {
			System.out.println("webdriver is not instalized");
			throw new IllegalStateException("webdriver is not instalized");
		}
		return driver.get();
	}

	public static ActionDriver getActionDriver() {
		if (actionDriver.get() == null) {
			System.out.println("actionDriver is not initialized");
			throw new IllegalStateException("actionDriver is not initialized");
		}
		return actionDriver.get();
	}

}