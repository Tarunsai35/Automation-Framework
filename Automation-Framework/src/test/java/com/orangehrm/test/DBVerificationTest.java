package com.orangehrm.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtendManager;

public class DBVerificationTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homePage  = new HomePage(getDriver());
	}
	
	@Test(dataProvider="emplVerification", dataProviderClass = DataProviders.class)
	public void verifyEmployeeNameVerificationFromDB(String emplID, String empName) {
		
		SoftAssert softAssert = getSoftAssert();
		
		ExtendManager.logStep("Logging with Admin Credentails");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		ExtendManager.logStep("click on PIM tab");
		homePage.clickOnPIMTab();
		
		ExtendManager.logStep("Search for Employee");
		homePage.employeeSearch(empName);
		staticWait(1);
		
		ExtendManager.logStep("Get the Employee Name from DB");
		String employee_id=emplID;
		
		//Fetch the data into a map
		
<<<<<<< HEAD
		Map<String,String> employeeDetails = DBConnection.getEmployeeDetails(employee_id);
		
		String emplFirstName = employeeDetails.get("firstName");
		String emplMiddleName = employeeDetails.get("middleName");
		String emplLastName = employeeDetails.get("lastName");
		
		String emplFirstAndMiddleName =(emplFirstName+" "+emplMiddleName).trim();
=======
		String empFirstName = employeeDetails.get("firstname");
		String empMiddleName = employeeDetails.get("middlename");
		String empLastName = employeeDetails.get("lastname");
		String empFirstAndMiddleName = (empFirstName+" "+empMiddleName).trim(); // Test fail by adding "test"
		
		ExtendManager.logStep("verfiy the employee first and middle name");
		Assert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(empFirstAndMiddleName),"First and Middle name are not matched");
//		softAssert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(empFirstAndMiddleName),"First and Middle name are not matched");
		
		ExtendManager.logStep("verfiy the employee Last name");
		Assert.assertTrue(homePage.verifyEmployeeLastName(empLastName),"Last name are not matched");
//		softAssert.assertTrue(homePage.verifyEmployeeLastName(empLastName),"Last name are not matched");
>>>>>>> 5eec507a2250a42f74360f7b4c4ae0350fab5edb
		
		//Validation for first and middle name
		ExtendManager.logStep("Verify the employee first and middle name");
		softAssert.assertTrue(homePage.verifyEmployeeFirstAndMiddleName(emplFirstAndMiddleName),"First and Middle name are not Matching");
		
		//validation for last name
		ExtendManager.logStep("Verify the employee last name");
		softAssert.assertTrue(homePage.verifyEmployeeLastName(emplLastName));
		
		ExtendManager.logStep("DB Validation Completed");
		
<<<<<<< HEAD
		softAssert.assertAll();

=======
//		softAssert.assertAll();
>>>>>>> 5eec507a2250a42f74360f7b4c4ae0350fab5edb
	}

}