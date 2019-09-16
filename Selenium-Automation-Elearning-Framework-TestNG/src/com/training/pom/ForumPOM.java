package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForumPOM {
	private WebDriver driver; 
	
	public ForumPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="tooldesc_6854")
	private WebElement forumImg;
	
	@FindBy(xpath="//a[contains(text(),'Group')]")
	private WebElement groupNametLink;
	
	@FindBy(xpath="//*[@title='Create thread']")
	private WebElement createThreadImg; 
	
	@FindBy(id="thread_post_title")
	private WebElement threadTitle;
	
	@FindBy(xpath="//*[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")	
	private WebElement threadText;
	
	@FindBy(xpath="//*[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']/p")	
	private WebElement threadTextReply;
	
	@FindBy(id="thread_SubmitPost")
	private WebElement submitThreadBtn;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement replyToThisMessageBtn;
	
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement confirmMsg;
	
	public void clickForumImage() {
		this.forumImg.click(); 
	}
	
	public void clickGroupNameLink() {
		this.groupNametLink.click(); 
	}
	
	public void clickCreateThreadImage() {
		this.createThreadImg.click(); 
	}
	
	public void sendThreadTitle(String title) {
		this.threadTitle.clear(); 
		this.threadTitle.sendKeys(title);
	}
	
	public void sendThreadText(String text) {
		this.threadText.clear(); 
		this.threadText.sendKeys(text);
	}
	
	public void sendThreadReplyText(String text) {
		this.threadTextReply.clear(); 
		this.threadTextReply.sendKeys(text);
	}
	
	public void clickSubmitThreadBtn() {
		this.submitThreadBtn.click(); 
	}
	
	public void clickReplyToThisMessageBtn() {
		this.replyToThisMessageBtn.click(); 
	}
	
	public String verifyReplyMessage() {
		String Message;
		Message = this.confirmMsg.getText();
		return Message;
	}
}
