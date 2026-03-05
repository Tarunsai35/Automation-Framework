package com.orangehrm.test;

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
	}
}
