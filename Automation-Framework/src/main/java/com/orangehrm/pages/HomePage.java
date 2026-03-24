package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define Locators using By Class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By profileIconName = By.className("oxd-userdropdown-name");
	private By logoutBtn = By.xpath("//a[text()='Logout']");
	private By LogoImage = By.xpath("//div[@class='oxd-brand-banner']//img");
	private By pimTab = By.xpath("//span[text()='PIM']");
	private By employeeSearch = By.xpath("//label[text()='Employee Name']/parent::div//following-sibling::div/div/div/input");
	private By SearchBtn = By.xpath("//button[@type='submit']");
	private By empFirstAndMiddleName = By.xpath("//div[@class='oxd-table-body']/div/div/div[3]");
	private By empLastName = By.xpath("//div[@class='oxd-table-body']/div/div/div[4]");
	
	
	//initialize the ActionDriver object by passing webDriver instance
/*	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	} */
	
	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}
	
	//Method to verify if admin tab is visible
	public boolean  isAdminToVisible() {
		return actionDriver.isDisplayed(adminTab);
	}
	
	public boolean verifyLogoImage() {
		return actionDriver.isDisplayed(LogoImage);
	}
	
	//Method to navigate to PIMTab
	public void clickOnPIMTab() {
		actionDriver.click(pimTab);
	}
	
	//Employee Search
	public void employeeSearch(String value) {
		actionDriver.enterText(employeeSearch, value);
		actionDriver.click(SearchBtn);
		actionDriver.scrollToElement(empFirstAndMiddleName);
	}
	
	//Verify employee first and middle name
	public boolean verifyEmployeeFirstAndMiddleName(String empFirstAndMiddleNameFromDB) {
		return actionDriver.compareTex(empFirstAndMiddleName, empFirstAndMiddleNameFromDB);
	}
	
	//Verify employee Last name
	public boolean verifyEmployeeLastName(String empLastNameFromDB) {
		return actionDriver.compareTex(empLastName, empLastNameFromDB);
	}
	
	//Method to perform Logout operation
	public void logout() {
		actionDriver.click(profileIconName);
		actionDriver.click(logoutBtn);
	}
	
	
	
}
