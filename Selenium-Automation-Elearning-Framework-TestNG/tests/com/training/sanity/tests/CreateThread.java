package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegisterCourse;
import com.training.pom.CourseDetailsPOM;
import com.training.pom.ForumPOM;
import com.training.pom.UserLogout;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.sanity.tests.LoginTests;
import com.training.sanity.tests.LogoutTests;



public class CreateThread {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RegisterCourse registerCourse;
	private CourseDetailsPOM courseDetailsPOM;
	private ForumPOM forumPOM;
	private UserLogout userLogout;
	private static Properties properties;
	public LoginTests loginTest;
	private LogoutTests logoutTests;
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
		forumPOM = new ForumPOM(driver);
		userLogout = new UserLogout(driver);		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		loginTest = new LoginTests();
		logoutTests = new LogoutTests();
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.close();
	}
	
	
	@Test(priority=1)
	public void registerCourse() throws InterruptedException {
		//login to aplication
		loginPOM.sendUserName("Sivamathi");
		loginPOM.sendPassword("1w2qaxsz");
		loginPOM.clickLoginBtn(); 
		//take the screenshot in the current step
		screenShot.captureScreenShot("login");	
		
		//search with course name "Selenium" and register for the course
		String actMsg="";
		registerCourse.clickHomePage();
		Thread.sleep(1000);
		registerCourse.clickCourseCatalog();
		Thread.sleep(2000);
		registerCourse.sendCourceName("Selenium course with assignment");
		registerCourse.clickSearch();
		Thread.sleep(2000);
		registerCourse.clickCourseItem();
		Thread.sleep(2000);
		//get the confirmation message
		actMsg=registerCourse.verifyAlertMessage();
		
		//Verify if the confirmation message is displayed
		Assert.assertTrue(actMsg.contains("User Sivamathi s (Sivamathi) has been registered to course"));
		screenShot.captureScreenShot("RegisterCourse");
	}
	
	@Test(priority=2)
	public void createThreadFromForum() throws InterruptedException {
		//method to go to course description tab and verify if the registered course is displayed
		courseDetailsPOM.clickMyCourseLink();
		Thread.sleep(1000);
		courseDetailsPOM.clickCourseItem();
		Thread.sleep(2000);
		
		forumPOM.clickForumImage();
		Thread.sleep(2000);
		
		forumPOM.clickGroupNameLink();
		Thread.sleep(2000);
		
		forumPOM.clickCreateThreadImage();
		Thread.sleep(4000);
		
		forumPOM.sendThreadTitle("Test123");
		Thread.sleep(3000);
		
		final WebElement threadTextFrame=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
				
		driver.switchTo().frame(threadTextFrame);
		forumPOM.sendThreadText("Test123");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		forumPOM.clickSubmitThreadBtn();
		Thread.sleep(2000);
		
		forumPOM.clickReplyToThisMessageBtn();
		Thread.sleep(6000);
		
		driver.switchTo().frame(threadTextFrame);
		forumPOM.sendThreadReplyText("Test reply");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		forumPOM.clickSubmitThreadBtn();
		Thread.sleep(2000);
		
		String actReplyMsg=forumPOM.verifyReplyMessage();
		
		//Verify if the confirmation message is displayed
		Assert.assertEquals(actReplyMsg, "The reply has been added");
		
		screenShot.captureScreenShot("createThreadfromForum");
	}
	
	@Test(priority=3)
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
