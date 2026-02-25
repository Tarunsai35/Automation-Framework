package ai.leadnest.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ai.leadnest.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public void setUpBrowser(String browser, boolean headless) {
		
		
		if (browser.equalsIgnoreCase("chrome")) {
			
			Log.info("=======================Browesr Open===================");
			Log.info("Opening Chrome");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

			if (headless) {
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
			}
			
			Log.info("Setting up webdriver..");
			driver = new ChromeDriver(options);

			
		} else if (browser.equalsIgnoreCase("firefox")) {
			
			Log.info("Opening firefox");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();

			if (headless) {
//              options.setHeadless(true);
				options.addArguments("-headless");
			}
			
			Log.info("Setting up webdriver..");
			driver = new FirefoxDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

//		String url = ConfigReader.getProperty("url");
//		driver.get(url);
		Log.info("Navigating to URL");
		driver.get("https://dev.leadnest.ai");
	}

	public void tearDown() {
		if (driver != null) {
			Log.info("==============Closing Browser=======================");
			driver.quit();
		}
	}
}
