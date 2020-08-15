package crmTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class LoginTest {

	WebDriver driver;
	String browser = null;

	@BeforeTest

	public void readConfig() {
		// InputStream //BufferReader //FileReader //Scanner
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream(".\\src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void startbrowser() {

		if (browser.equalsIgnoreCase("chrome")) {

			// setting up property
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			// creating web driver instance
			driver = new ChromeDriver();

		}
		
		
		// Maximizing Browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// get to the site
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void logInTest1() throws InterruptedException {

		// Element Library
		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.name("login");
		By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(text(), 'Dashboard')]");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();

		waitForElement(driver, 3, DASHBOARD_BUTTON_LOCATOR);

		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
		String title = driver.getTitle();
		System.out.println(title);
		
		//Assert.assertEquals("Dashboard- iBilling", title);
		Assert.assertEquals("wrong title", "Dashboard- iBilling", title);

	}

	@Test(priority=2)
	   public void addCustomersTest() throws InterruptedException {
		//Element Library
	  By USER_NAME_FIELD = By.id("username");
	  By PASSWORD_FIELD =  By.id("password"); 
	  By SIGNIN_BUTTON =By.name("login");
	  By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
	  By CUSTOMERS_BUTTON =  By.xpath("//span[contains(text(), 'Customers')]  ");
	  By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]"); 
	  By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(), 'Add Contact')]");
	  By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
	  By COMPANY_NAME_FIELD = By.xpath("//input[@id='company_name']"); 
	  By EMAIL_FIELD = By.xpath("//input[@id='email']"); 
	  By PHONE_FIELD =By.xpath("//input[@id='phone']"); 
	  By ADDRESS_FIELD = By.xpath("/input[@id='address']"); 
	  By CITY_FIELD = By.xpath("//input[@id='city']");
	  By STATE_REGION_FIELD = By.xpath("//input[@id='state']"); 
	  By ZIP_FIELD = By.xpath("//input[@id='zip']");
	  By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']"); 
	  By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(), 'List Contacts']");
	 
	// Login Data
			String loginId = "demo@techfios.com";
			String password = "abc123";

	 // Test Data

	String fullName = "Test Spring";
	String companyName = "Techfios";
	String emailAddress = "@gmail.com";
	String phoneNumber = "2354564789";
	
	// Login Data
			
			driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
			driver.findElement(PASSWORD_FIELD).sendKeys(password);
			driver.findElement(SIGNIN_BUTTON).click();

		// Validate Dashboard page
			
	waitForElement(driver, 3, DASHBOARD_BUTTON);
	String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON).getText();
	
	System.out.println(dashboardValidationText);
	Assert.assertEquals("not matching", "Dashboard", dashboardValidationText);

	driver.findElement(CUSTOMERS_BUTTON).click();
	driver.findElement(ADD_CUSTOMER_BUTTON).click();
	
	waitForElement(driver, 3, ADD_CONTACT_LOCATOR);
		
		//Generate Random Number
		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);
		
		//Fill out add customers form
		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + randomNum);
		driver.findElement(EMAIL_FIELD).sendKeys(randomNum + emailAddress);
		
		}

	//@AfterMethod
	public void tearDown() {

		driver.close();
		driver.quit();
	}

	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
}
 