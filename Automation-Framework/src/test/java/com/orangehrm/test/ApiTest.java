package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.utilities.ApiUtility;
import com.orangehrm.utilities.ExtendManager;

import io.restassured.response.Response;

public class ApiTest {

	@Test
	public void verifyGetUserAPI() {

		// Step1: Define API Endpoint
		String endPoint = "";
		ExtendManager.logStep("API EndPoint :" + endPoint);

		// Step2: Send Get Request
		ExtendManager.logStep("sending Get request to the Api");
		Response response = ApiUtility.sendGetRequest(endPoint);

		// Step3: validate status code
		ExtendManager.logStep("Validating Api response status code");
		boolean isStatusCodeValid = ApiUtility.validateStatusCode(response, 200);

		Assert.assertTrue(isStatusCodeValid, "Status code is not as expected");

		if (isStatusCodeValid) {
			ExtendManager.logStepValidationForAPI("Status Code Validation Passed!");
		} else {
			ExtendManager.logFailureAPI("Status Code Validation Failed!");
		}

		// Step4: validate user name
		ExtendManager.logStep("Validating response body for username");
		String userName = ApiUtility.getJsonValue(response, "username");
		boolean isUserNamevalid = "".equals(userName);
		Assert.assertTrue(isUserNamevalid, "username is not valid");

		if (isUserNamevalid) {
			ExtendManager.logStepValidationForAPI("Username Validation Passed!");
		} else {
			ExtendManager.logFailureAPI("Username Validation Failed!");
		}
	}
}
