package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtendManager;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}

	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void verifyValidLoginTest(String username, String password) {
		
//		ExtendManager.startTest("Valid Login Test"); ----This has been implemented in testListener
		System.out.println("Running testMethod1 on thread: "+Thread.currentThread().getId());
		ExtendManager.logStep("Navigating to login page entering username and password");
		loginPage.login(username, password);
		ExtendManager.logStep("Verifying admin tab is visible or not");
		Assert.assertTrue(homePage.isAdminToVisible(), "Admin tab should be visible after successful login");
		ExtendManager.logStep("Validation Successful");
		homePage.logout();
		ExtendManager.logStep("Logged out Successfully!");
	}
	
	@Test(dataProvider = "inValidLoginData", dataProviderClass = DataProviders.class)
	public void verifyInvalidLoginTest(String username, String password) {
//		ExtendManager.startTest("Invalid Login Test");  --This has been implemented in testListener
		System.out.println("Running testMethod2 on thread: "+Thread.currentThread().getId());
		ExtendManager.logStep("Navigating to login page entering username and password");
		loginPage.login(username, password);
		String expectedErrorMessage = "Invalid credentials1";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test failed: Invalid Error Message");
		ExtendManager.logStep("Validation Successful");
		ExtendManager.logStep("Logged out Successfully!");
		
	}
	
	@Test
	public void main1() {
		ExtendManager.startTest("Test Method");
		System.out.println("Running testMethod3 on thread: "+Thread.currentThread().getId());
		System.out.println("testing....");
		System.out.println("testing2....");
		
	}
	
	
	
}
