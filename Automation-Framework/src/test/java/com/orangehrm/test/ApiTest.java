package com.orangehrm.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.utilities.ApiUtility;
import com.orangehrm.utilities.ExtendManager;

import io.restassured.response.Response;

public class ApiTest {

	@Test //(retryAnalyzer = RetryAnalyzer.class)
	public void verifyGetUserAPI() {

		SoftAssert softAssert = new SoftAssert();
		
		ExtendManager.startTest("Valid Api Test");
		// Step1: Define API Endpoint
		String endPoint = "https://jsonplaceholder.typicode.com/users/1";
		ExtendManager.logStep("API EndPoint :" + endPoint);

		// Step2: Send Get Request
		ExtendManager.logStep("sending Get request to the Api");
		Response response = ApiUtility.sendGetRequest(endPoint);

		// Step3: validate status code
		ExtendManager.logStep("Validating Api response status code");
		boolean isStatusCodeValid = ApiUtility.validateStatusCode(response, 200);

//		Assert.assertTrue(isStatusCodeValid, "Status code is not as expected");
		softAssert.assertTrue(isStatusCodeValid, "Status code is not as expected");

		if (isStatusCodeValid) {
			ExtendManager.logStepValidationForAPI("Status Code Validation Passed!");
		} else {
			ExtendManager.logFailureAPI("Status Code Validation Failed!");
		}

		// Step4: validate user name
		ExtendManager.logStep("Validating response body for username");
		String userName = ApiUtility.getJsonValue(response, "username");
		boolean isUserNamevalid = "Bret".equals(userName);
		
//		Assert.assertTrue(isUserNamevalid, "username is not valid");
		softAssert.assertTrue(isUserNamevalid, "username is not valid");

		if (isUserNamevalid) {
			ExtendManager.logStepValidationForAPI("Username Validation Passed!");
		} else {
			ExtendManager.logFailureAPI("Username Validation Failed!");
		}
		
		// Step4: validate user email
		ExtendManager.logStep("Validating response body for email");
		String userEmail = ApiUtility.getJsonValue(response, "email");
		boolean isEmailvalid = "Sincere@april.biz".equals(userEmail);
		
//		Assert.assertTrue(isEmailvalid, "username is not valid");
		softAssert.assertTrue(isEmailvalid, "username is not valid");
		
		if (isEmailvalid) {
			ExtendManager.logStepValidationForAPI("Email Validation Passed!");
		} else {
			ExtendManager.logFailureAPI("Email Validation Failed!");
		}
		
		softAssert.assertAll();
		
		
	}
}
