package ai.leadnest.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ai.leadnest.utils.ElementLocator;
import ai.leadnest.utils.Log;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	//Actions
	public void clickOnLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.LOGIN_BTN.getLocator())).click();
	}

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.EMAIL_FIELD.getLocator()))
				.sendKeys(email);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.PASSWORD_FIELD.getLocator()))
				.sendKeys(password);
	}

	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.SUBMIT_BTN.getLocator())).click();
	}
	
	//Methods
	public void login(String email, String password) {
		Log.info("Click on Login button");
		clickOnLogin();
		Log.info("Adding credentials");
		enterEmail(email);
		enterPassword(password);
		Log.info("Click on Submit button");
		clickSubmit();
	}
}
