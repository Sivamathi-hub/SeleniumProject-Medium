package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLogout {
	private WebDriver driver; 
	
	public UserLogout(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='img-circle']")
	private WebElement imgUserIcon; 
	
	@FindBy(xpath="//*[@class='user-body']/a")
	private WebElement linkUserMenu; 
	
	@FindBy(id="logout_button")
	private WebElement logoutBtn; 
	
	
	public void clickUserIcon() {
		this.imgUserIcon.click(); 
	}
	
	public void clickUserLogout() {
		this.logoutBtn.click(); 
	}
}
