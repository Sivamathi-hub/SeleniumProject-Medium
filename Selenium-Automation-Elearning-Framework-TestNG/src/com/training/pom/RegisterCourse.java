package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterCourse {
	private WebDriver driver; 
	
	public RegisterCourse(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Homepage']")
	private WebElement Homepage; 
	
	@FindBy(linkText="Course catalog")
	private WebElement courseCatalog;
	
	@FindBy(name="search_term")
	private WebElement courseName; 
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	private WebElement Search;
	
	@FindBy(xpath="//*[@class='col-sm-8']/a[1]")
	private WebElement selectCourse;
	
	@FindBy(xpath="//*[@class='alert alert-info']")
	private WebElement alertMessage;
	
	public void clickHomePage() {		
		this.Homepage.click();
	}
	
	public void clickCourseCatalog() {
		this.courseCatalog.click();
	}
	
	public void sendCourceName(String courseName) {
		this.courseName.clear(); 
		this.courseName.sendKeys(courseName);  
	}
	public void clickSearch() {
		this.Search.click();
	}
	
	public void clickCourseItem() {
		this.selectCourse.click();
	}
	
	public String verifyAlertMessage() {
		String Message;
		Message = this.alertMessage.getText();
		return Message;
	}
}
