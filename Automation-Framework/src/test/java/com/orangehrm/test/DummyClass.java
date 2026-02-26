package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class DummyClass extends BaseClass{

	@Test
	public void dummyclass() {
		String title = driver.getTitle();
		System.out.println(title);
	}
}
