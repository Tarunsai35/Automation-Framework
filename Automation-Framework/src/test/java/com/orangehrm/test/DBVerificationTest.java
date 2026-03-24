package com.orangehrm.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.base.ConfigReader;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.ExtendManager;

public class DBVerificationTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test
	public void verifyEmployeeNameVerificationFromDB() {
		ExtendManager.logStep("Logging with admin credentails..");
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		
		ExtendManager.logStep("Click on PIM tab");
		homePage.clickOnPIMTab();
		
		ExtendManager.logStep("Serach for Employee");
		homePage.employeeSearch("sai");
		
		ExtendManager.logStep("Get the Employee name from DB");
		String employee_id = "2";
		
		//Fetch the data into map
		Map<String, String> employeeDetails = DBConnection.getEmployeeDetails(employee_id);
		
		String empFirstName = employeeDetails.get("firstName");
		String empMiddleName = employeeDetails.get("middleName");
		String empLastName = employeeDetails.get("lastName");
		String empFirstAndMiddleName = (empFirstName+" "+empMiddleName).trim();
		
		ExtendManager.logStep("verfiy the employee first and middle name");
		Assert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(empFirstAndMiddleName),"First and Middle name are not matched");
		
		ExtendManager.logStep("verfiy the employee Last name");
		Assert.assertTrue(homePage.verifyEmployeeLastName(empLastName),"Last name are not matched");
		
		ExtendManager.logStep("DB Validation Completed..");
	}
	
	
}
