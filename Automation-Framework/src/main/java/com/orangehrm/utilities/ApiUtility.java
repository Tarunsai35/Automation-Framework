package com.orangehrm.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtility {

	//Method to send Get Request
	public static Response sendGetRequest(String endPoint) {
		return RestAssured.get(endPoint);
	}
	
	// Method to send Post Request
	public static void sendPostRequest(String endPoint, String payLoad) {
		RestAssured.given().header("Content-type","application/json")
						   .body(payLoad)
						   .post(endPoint);
	}
	
	//Method to validate the response status
	public static boolean validateStatusCode(Response response, int statusCode) {
		return response.getStatusCode() == statusCode;
	}
	
	//Method to extract value from Json response
	public static String getJsonValue(Response response, String value) {
		return response.jsonPath().getString(value);
	}
	
	
}