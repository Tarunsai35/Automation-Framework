package com.orangehrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtendManager;

public class TestListener implements ITestListener{

	//Triggered when a test starts
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		//start logging in extend reports
		ExtendManager.startTest(testName);
		ExtendManager.logStep("Test Started: "+testName);
	}

	// Triggered when a Test succeed
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendManager.logStepWithScreenshot(BaseClass.getDriver(), "Test Passed Successfully!","Test End: "+ testName);
		}else {
			ExtendManager.logStepValidationForAPI("Test End: "+ testName);
		} 
	}

	// Triggered when a Test fails
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage = result.getThrowable().getMessage();
		ExtendManager.logStep(failureMessage);
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendManager.logFailure(BaseClass.getDriver(),"Test Failed! "," Test End: "+ testName);
		}else {
			ExtendManager.logFailureAPI("Test End: "+ testName);
		} 
	}

	//Triggered when a test Skips
	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		ExtendManager.logSkip("Test Skipped "+testname);
	}

	//Triggered when a suit Starts
	@Override
	public void onStart(ITestContext context) {
		// Initialize the extend report
		ExtendManager.getReporter();
	}

	//Triggered when the suit ends
	@Override
	public void onFinish(ITestContext context) {
		// Flush the extend reports
		ExtendManager.endTest();
	}

}
