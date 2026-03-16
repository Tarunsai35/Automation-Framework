package com.orangehrm.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;
import com.orangehrm.base.ConfigReader;
import com.orangehrm.utilities.ExtendManager;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
//	private static final Logger logger = LoggerManager.getlogger(ActionDriver.class);

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		String implicitWaitStr = ConfigReader.getProperty("implicitWait");
		int implicitWait = implicitWaitStr != null ? Integer.parseInt(implicitWaitStr) : 30;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(implicitWait));
		logger.info("Webdriver instance is created.");
	}

	// Method to Click an element
	public void click(By by) {
		try {
			applyBorder(by, "green");
			waitForElementToBeClick(by);
			driver.findElement(by).click();
			ExtendManager.logStep("Clicked an element --> "+getElementDescription(by));
			logger.info("Clicked an element -->" + getElementDescription(by));
		} catch (Exception e) {
			applyBorder(by, "red");
			ExtendManager.logFailure(BaseClass.getDriver(), "Unable to click element: ", getElementDescription(by) + "_unable to click");
			logger.error("Unable to Click Element: " + e.getMessage());
		}
	}

	// Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			applyBorder(by, "green");
//			driver.findElement(by).clear();
//			driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
			logger.info("Enter text on -->" + getElementDescription(by) + " " + value);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to enter the value:" + e.getMessage());
		}
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			applyBorder(by, "green");
			return driver.findElement(by).getText();
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to get the Text:" + e.getMessage());
			return "";
		}
	}

	// Method to compare two Text
	public boolean compareTex(By by, String Expected) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (Expected.equals(actualText)) {
				applyBorder(by, "green");
				logger.info("Text are matching " + actualText + " equal " + Expected);
				ExtendManager.logStepWithScreenshot(BaseClass.getDriver(), "Compare Text", "Text verify successfully!"+actualText+ " equals "+ Expected);
				return true;
			} else {
				applyBorder(by, "red");
				logger.error("Text are not matching " + actualText + " equal " + Expected);
				ExtendManager.logFailure(BaseClass.getDriver(), "Text comparison Failed!", "Text comparison failed!"+actualText+ " not equals "+ Expected);
				return false;
			}
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("unable to compare text:" + e.getMessage());
		}
		return false;
	}

	// Method to check if an element is displayed
	public boolean isDisplayed(By by) {
		/*
		 * try { waitForElementToBeVisible(by); boolean isDisplayed =
		 * driver.findElement(by).isDisplayed(); if (isDisplayed) {
		 * System.out.println("Element is visible"); return isDisplayed; } else { return
		 * isDisplayed; } } catch (Exception e) {
		 * System.out.println("elemnet is not displayed :" + e.getMessage()); return
		 * false; }
		 */

		try {
			waitForElementToBeVisible(by);
			applyBorder(by, "green");
			logger.info("Element is displayed -->" + getElementDescription(by));
			ExtendManager.logStep("Element is displayed --> "+ getElementDescription(by));
			ExtendManager.logStepWithScreenshot(BaseClass.getDriver(), "Element is displayed :", "Element is displayed "+getElementDescription(by));
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Element is not displayed:" + e.getMessage());
			ExtendManager.logFailure(BaseClass.getDriver(),"Element is not displayed : ","element is not displayed :"+ getElementDescription(by));
			return false;
		}

	}

	// Scroll to an element
	public void scrollToElement(By by) {
		try {
			applyBorder(by, "green");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to loacte element:" + e.getMessage());
		}
	}

	// Wait for Element To be clickable
	private void waitForElementToBeClick(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error("element is not clickable: " + e.getMessage());
		}
	}

	// wait for Element To Be Visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Element is not visible: " + e.getMessage());
		}
	}

	// Method to get the description of an element using By locator

	public String getElementDescription(By locator) {

		if (driver == null)
			return "driver is null";
		if (locator == null)
			return "locator is null";

		try {
			// finding the element using the locator
			WebElement element = driver.findElement(locator);

			// get elements Attributes
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String classname = element.getDomAttribute("class");
			String text = element.getText();
			String placeHolder = element.getDomAttribute("placeholder");

			// Return the description based on element attributes
			if (isNotEmpty(name)) {
				return "Element with name :" + name;
			} else if (isNotEmpty(id)) {
				return "Element with id :" + id;
			} else if (isNotEmpty(classname)) {
				return "Element with className :" + classname;
			} else if (isNotEmpty(placeHolder)) {
				return "Element with placeholder :" + placeHolder;
			} else if (isNotEmpty(text)) {
				return "Element with Text :" + truncate(text, 50);
			}
		} catch (Exception e) {
			logger.info("Unable to describe the element" + e.getMessage());
		}
		return "";

	}

	// Utility method to check a string is not null or empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();

	}

	// Utility method to truncate long string
	private String truncate(String value, int maxlength) {
		if (value == null || value.length() <= maxlength) {
			return value;
		}
		return value.substring(0, maxlength) + "...";
	}

	// Utility Method for border an element
	public void applyBorder(By by, String color) {
		try {
			// Locate the element
			WebElement element = driver.findElement(by);
			// Apply the border
			String script = "arguments[0].style.border='3px solid" + color + "'";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script, element);
			logger.info("Applied the border color " + color + " to element: " + getElementDescription(by));
		} catch (Exception e) {
			logger.warn("Failed to apply the border to an element: " + getElementDescription(by), e.getMessage());
		}
	}
	
	
}
