package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.ConfigReader;

public class ActionDriver  {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		String implicitWaitStr = ConfigReader.getProperty("implicitWait");
		int implicitWait = implicitWaitStr != null ? Integer.parseInt(implicitWaitStr) : 30;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(implicitWait));
	}

	
	// Method to Click an element
	public void click(By by) {
		try {
			waitForElementToBeClick(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to Click Element:" + e.getMessage());
		}
	}

	// Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Unable to enter the value:" + e.getMessage());
		}
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the Text:" + e.getMessage());
			return "";
		}
	}

	// Method to compare two Text
	public void compareTex(By by, String Expected) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (Expected.equals(actualText)) {
				System.out.println("Text are matching" + actualText + "equal" + Expected);
			} else {
				System.out.println("Text are not matching" + actualText + "equal" + Expected);
			}
		} catch (Exception e) {
			System.out.println("unable to compare text:" + e.getMessage());
		}
	}

	// Method to check if an element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			if (isDisplayed) {
				System.out.println("Element is visible");
				return isDisplayed;
			} else {
				return isDisplayed;
			}
		} catch (Exception e) {
			System.out.println("elemnet is not displayed :" + e.getMessage());
			return false;
		}
	}

	// Scroll to an element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0]", "scrollIntoView(true)", element);
		} catch (Exception e) {
			System.out.println("Unable to loacte element:" + e.getMessage());
		}
	}

	// Wait for Element To be clickable
	private void waitForElementToBeClick(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("element is not clickable: " + e.getMessage());
		}
	}

	// wait for Element To Be Visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible: " + e.getMessage());
		}
	}

}
