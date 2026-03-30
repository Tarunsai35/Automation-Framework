package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtendManager;

public class DummyClass extends BaseClass{

	@Test
	public void dummyclass() {
		//First test ---testing1
		ExtendManager.logStep("DummyTest1 Test"); //--This has been implemented in testListener
		String title = getDriver().getTitle();
		ExtendManager.logStep("Verify the title");
		System.out.println(title);
		Assert.assertEquals(title, "OrangeHRM");
		System.out.println("Test passed - Title is matching");
	}
}