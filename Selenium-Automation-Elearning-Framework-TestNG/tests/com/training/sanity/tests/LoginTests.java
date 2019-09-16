package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegisterCourse;
import com.training.pom.CourseDetailsPOM;
import com.training.pom.UserLogout;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import java.util.List;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RegisterCourse registerCourse;
	private CourseDetailsPOM courseDetailsPOM;
	private UserLogout userLogout;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		//creating instance for training.pom classess
		loginPOM = new LoginPOM(driver);
		registerCourse = new RegisterCourse(driver);
		courseDetailsPOM = new CourseDetailsPOM(driver);
		userLogout = new UserLogout(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
	}
	
	@Test(priority=3)
	public void closeBrowser(){
		driver.quit();
	}
	@Test(priority=1)
	// method to login to application
	public void validLoginTest() {
		//enter user name and password and click on login button
		loginPOM.sendUserName("Sivamathi");
		loginPOM.sendPassword("1w2qaxsz");
		loginPOM.clickLoginBtn(); 
		//take the screenshot in the current step
		screenShot.captureScreenShot("login");
	}
	
}
