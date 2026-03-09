package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class LoginPage {

	private ActionDriver actionDriver;

	// Define Locators using By Class
	private By UserNameField = By.cssSelector("input[placeholder='Username']");
	private By PasswordField = By.cssSelector("input[placeholder='Password']");
	private By LoginBtn = By.cssSelector("button[type='submit']");
	private By ErroeMessage = By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']");

	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
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
	public void verifyErrorMessage(String Expected) {
		actionDriver.compareTex(ErroeMessage, Expected);
	}

}
