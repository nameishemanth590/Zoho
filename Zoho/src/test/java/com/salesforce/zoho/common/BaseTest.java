package com.salesforce.zoho.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.salesforce.zoho.generic.IAutoConstants;
import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.HomePage;
import com.salesforce.zoho.pages.common.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConstants {
	
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public HomePage homePage;
	
	@Parameters({"browserName", "url", "implicit", "explicit"})
	@BeforeClass(alwaysRun=true)
	public void openApp(@Optional(DEFAULT_BROWSER) String browserName,
						@Optional(APP_URL) String url,
						@Optional(ITO) String implicit,
						@Optional(ETO) String explicit) {
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("web.notifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else {
			Assert.fail("Only chrome and Firefox is Supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(implicit), TimeUnit.SECONDS);
		driver.get(url);
		webActionUtil = new WebActionUtil(driver, Long.parseLong(explicit));
	}
	
	@Parameters({"user","pwd"})
	@BeforeMethod(alwaysRun=true)
	public void loginToApp(@Optional(DEFAULT_USER)String user,
						   @Optional(DEFAULT_PWD)String pwd) {
		LoginPage loginPage = new LoginPage(driver, webActionUtil);
		homePage = loginPage.login(user, pwd);
	}
	
	@AfterMethod(alwaysRun=true)
	public void logout(ITestResult result) {
		
		String testCase=result.getName();
		int status = result.getStatus();
		
		if(status==ITestResult.FAILURE) {
			System.out.println("Taking Screenshot for "+testCase);
		}
		
		homePage.logout();
	}
	
	
	@AfterClass(alwaysRun=true)
	public void closeApp() {
		driver.quit();
	}
}
