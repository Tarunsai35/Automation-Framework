package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyValidLoginTest() {
		loginPage.login("Admin", "admin123");
		Assert.assertTrue(homePage.isAdminToVisible(), "Admin tab should be visible after successful login");
		homePage.logout();
	}
	
	@Test
	public void verifyInvalidLoginTest() {
		loginPage.login("Admin", "Admin");
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test failed: Invalid Error Message");
		
	}
	
	@Test
	public void main1() {
		System.out.println("testing....");
		System.out.println("testing2....");
		
	}
	
	
	
}
