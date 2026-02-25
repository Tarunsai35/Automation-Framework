package ai.leadnest.login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ai.leadnest.base.BaseClass;
import ai.leadnest.pages.LoginPage;
import ai.leadnest.utils.Log;

public class LoginWithValidData extends BaseClass {

	LoginPage loginPage;

	@BeforeMethod
	public void setup() {

		setUpBrowser("chrome", false);
		Log.info("Started login test...");
		loginPage = new LoginPage(driver);
		loginPage.login("tarunsai5555@gmail.com", "   @");
		
	}

	@Test
	public void Promotional() {

		Log.info("Click on promotional");
		driver.findElement(By.xpath("//span[text()='Promotional']")).click();
		Log.info("Open dynamic dropdown and Click on Email");
//		driver.findElement(By.xpath("(//a[@class='dropdown-sub-item app-dynamic-link'])[4]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='E-Mail']")).click();
		String emailHeader = driver.findElement(By.xpath("div[class='lead-main-innercontent-heading'] h2")).getText();
		Log.info("Verifying page heading");
		Assert.assertEquals(emailHeader, "SMS Notifications");

//		driver.findElement(By.xpath("//div[@class='d-flex mt-5']//a//button")).click();
	}

	@Test(dependsOnMethods = "Promotional")
	public void Email_confg() {

		driver.findElement(By.xpath("(//div[@class='row'])[2]//div//div//a")).click();

		String header = driver.findElement(By.xpath("//div[@class='lead-main-innercontent-heading']//h2")).getText();

		Assert.assertEquals(header, "Email Configuration");

		driver.findElement(By.id("continue-setup")).click();
	}

	@AfterMethod
	public void tearDownTest() {
		tearDown();
	}
}
