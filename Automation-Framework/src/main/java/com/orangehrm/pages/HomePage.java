package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define Locators using By Class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By profileIcon = By.className("oxd-userdropdown-img");
	private By logoutBtn = By.xpath("//a[text()='Logout']");
	private By LogoImage = By.xpath("//div[@class='oxd-brand-banner']/img");
	
	//initialize the ActionDriver object by passing webDriver instance
/*	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
		System.out.println("Action driver is created");
	} */
	
	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
		System.out.println("created action driver");
	}
	
	//Method to verify if admin tab is visible
	public boolean  isAdminToVisible() {
		return actionDriver.isDisplayed(adminTab);
	}
	
	public boolean verifyLogoImage() {
		return actionDriver.isDisplayed(LogoImage);
	}
	
	//Method to perform Logout operation
	public void logout() {
		actionDriver.click(profileIcon);
		actionDriver.click(LogoImage);
	}
	
	
	
}
