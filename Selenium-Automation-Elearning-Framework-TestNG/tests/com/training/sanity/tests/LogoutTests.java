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

public class LogoutTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RegisterCourse registerCourse;
	private CourseDetailsPOM courseDetailsPOM;
	private UserLogout userLogout;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
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
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyUserMenuLinks() throws InterruptedException {
		userLogout.clickUserIcon();
		Thread.sleep(1000);		
		
		//verify displayed menu links are as expected or not
		String[] expLinks = new String[]{"Inbox","My certificates","Logout"};
		
		//get the menu link text value
		List<WebElement> tot = driver.findElements(By.xpath("//*[@class='user-body']/a"));
		String[] ltext = new String[tot.size()];
		for (int i=0;i<tot.size();i++){
			//System.out.println("Link Name is:"+tot.get(i).getText());
			ltext[i]=tot.get(i).getAttribute("innerText").trim();
			//System.out.println("Inner text Name is:"+ltext[i]);
		}
		
		//verify if the links text are as expected
		for (int i=0;i<tot.size();i++){			
			Assert.assertEquals(ltext[i],expLinks[i]);			
		}		
		//take the screenshot
		screenShot.captureScreenShot("verifyUserMenuLinks");
	}
	
	@Test(priority=2)
	public void userLogout() throws InterruptedException {
		//method to log out of the application
		String expTitle="e Learning - My education";		
		userLogout.clickUserLogout();
		Thread.sleep(2000);
		
		//verify if the home page is displayed after user logged out
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,expTitle);		
		screenShot.captureScreenShot("userLogout");
	}
}
