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

public class HomePageTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void verifyOrangeHRMLogo(String username, String password) {
//		ExtendManager.startTest("Home Page verify Logo Test"); --This has been implemented in testListener
		System.out.println("Running testMethod1 on thread: "+Thread.currentThread().getId());
		loginPage.login(username, password);
		ExtendManager.logStep("Verify Logo is visible or not");
		Assert.assertTrue(homePage.verifyLogoImage(),"Logo is not visible");
		ExtendManager.logStep("Validation Successful");
		ExtendManager.logStep("Logged out Successfully!");
	}
	
}
