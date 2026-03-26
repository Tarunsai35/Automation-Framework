package com.orangehrm.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {

	private ActionDriver actionDriver;
	public static final Logger logger = BaseClass.logger;
	
	// Define Locators using By Class
	private By UserNameField = By.cssSelector("input[placeholder='Username']");
	private By PasswordField = By.cssSelector("input[placeholder='Password']");
	private By LoginBtn = By.xpath("//button[@type='submit']");
	private By ErroeMessage = By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']");

	//Initialize the ActionDriver object by passing web driver instance
/*	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
		System.out.println("Action driver is created");
	} */
	
	public LoginPage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
		logger.info("created action driver");
	}

	// method to perform login
	public void login(String userName, String password) {
		actionDriver.enterText(UserNameField, userName);
		actionDriver.enterText(PasswordField, password);
		actionDriver.click(LoginBtn);
	}

	// Method to check if error message is displayed
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(ErroeMessage);
	}

	// Method to get the text from error message
	public void getErrorMessageText() {
		actionDriver.getText(ErroeMessage);
	}

	// verify if error is correct or not
	public boolean verifyErrorMessage(String Expected) {
		return actionDriver.compareText(ErroeMessage, Expected);
	}

}
