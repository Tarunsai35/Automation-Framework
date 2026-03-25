package com.orangehrm.listeners;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtendManager;
import com.orangehrm.utilities.RetryAnalyzer;
//import com.orangehrm.utilities.RetryAnalyzer;

public class TestListener implements ITestListener, IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
 
	// Triggered when a test starts
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		// Start logging in Extent Reports
		ExtendManager.startTest(testName);
		ExtendManager.logStep("Test Started: " + testName);
	}

	// Triggered when a Test succeeds
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		if (!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendManager.logStepWithScreenshot(BaseClass.getDriver(), "Test Passed Successfully!",
					"Test End: " + testName + " - ✔ Test Passed");
		} else {
			ExtendManager.logStepValidationForAPI("Test End: " + testName + " - ✔ Test Passed");
		}

	}

	// Triggered when a Test Fails
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage = result.getThrowable().getMessage();
		ExtendManager.logStep(failureMessage);
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendManager.logFailure(BaseClass.getDriver(), "Test Failed!", "Test End: " + testName + " - ❌ Test Failed");
		}
		else {
			ExtendManager.logFailureAPI("Test End: " + testName + " - ❌ Test Failed");
		}
	}

	// Triggered when a Test skips
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtendManager.logSkip("Test Skipped " + testName);
	}

	// Triggered when a suite Starts
	@Override
	public void onStart(ITestContext context) {
		// Initialize the Extent Reports
		ExtendManager.getReporter();
	}

	// Triggered when the suite ends
	@Override
	public void onFinish(ITestContext context) {
		// Flush the Extent Reports
		ExtendManager.endTest();

	}

}