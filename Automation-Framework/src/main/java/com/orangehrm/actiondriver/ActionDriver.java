package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	//Method to compare two Text
	public void compareTex(By by, String Expected) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(Expected.equals(actualText)) {
				System.out.println("Text are matching"+actualText+"equal"+Expected);
			}else {
				System.out.println("Text are not matching"+actualText+"equal"+Expected);
			}
		} catch (Exception e) {
			System.out.println("unable to compare text:"+e.getMessage());
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
