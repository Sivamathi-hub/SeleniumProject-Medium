package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseDetailsPOM {
	private WebDriver driver; 
	
	public CourseDetailsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='My courses']")
	private WebElement myCourseLink; 
	
	@FindBy(xpath="//*[@class='col-md-2']/a[1]")
	private WebElement courseItem;
	
	@FindBy(xpath="//*[@class='content']/a[1]")
	private WebElement courseDescription; 
	
	@FindBy(id="toolimage_6859")
	private WebElement courseAssignment; 
	
	@FindBy(xpath="//tbody/tr[2]/td[2]/a")
	private WebElement assignmentLink;
	
	@FindBy(xpath="//div/a/img[@title='Upload my assignment']")
	private WebElement uploadAssignmentImg;
	
	@FindBy(id="tabs2")
	private WebElement uploadSimple;
	
	@FindBy(id="form-work_file")
	private WebElement chooseFile;
	
	@FindBy(id="form-work_submitWork")
	private WebElement uploadBtn;
	
	@FindBy(xpath="//tr[2]/td[2]/div[contains(text(),'xlsx')]")
	private WebElement uploadedAssignmentNameLink;
	
	public void clickMyCourseLink() {
		this.myCourseLink.click(); 
	}
	
	public void clickCourseItem() {
		this.courseItem.click(); 
	}
	
	public void clickCourseAssignment() {
		this.courseAssignment.click(); 
	}
	public void clickAssignmentLink() {
		this.assignmentLink.click(); 
	}
	
	public void clickUploadAssignmentImage() {
		this.uploadAssignmentImg.click(); 
	}
	
	public void clickUploadSimpleTab() {
		this.uploadSimple.click(); 
	}
	
	public void sendFileChooseFileBtn() {
		this.chooseFile.sendKeys("C:\\Users\\SIVAMATHIS\\Desktop\\SE_Medium_Assign1.xlsx"); 
	}
	
	public void clickUploadBtn() {
		this.uploadBtn.click(); 
	}
	
	public void verifyAssignmentNameExist() {
		if (this.uploadedAssignmentNameLink.isDisplayed()) {
			System.out.println("File uploaded successfully");
		}
		else {
			System.out.println("File upload NOT successfull");
		}
			
	}
	
}
