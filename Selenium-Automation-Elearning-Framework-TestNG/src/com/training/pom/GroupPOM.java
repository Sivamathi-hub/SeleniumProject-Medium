package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupPOM {
	private WebDriver driver; 
	
	public GroupPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="tooldesc_6857")
	private WebElement groupImg;
	
	@FindBy(xpath="//a[contains(text(),'register')]")
	private WebElement registerGroupBtn;
	
	@FindBy(xpath="//span[contains(text(),'my group')]/preceding-sibling::a")
	private WebElement groupLink; 
	
	@FindBy(xpath="//*[@title='Chat']")	
	private WebElement groupChatImg;
	
	@FindBy(xpath="//*[@class='emoji-wysiwyg-editor']")	
	private WebElement chatTextbox;
	
	@FindBy(id="chat-send-message")
	private WebElement sendChatMsgBtn;
	
	@FindBy(xpath="//*[@class='chat-message-block-content']")
	private WebElement chatContent;
	
	public void clickGroupImage() {
		this.groupImg.click(); 
	}
	
	public void clickRegisterGroupBtn() {
		this.registerGroupBtn.click(); 
	}
	
	public void clickGroupLink() {
		this.groupLink.click(); 
	}
	
	public void clickGroupChatImg() {
		this.groupChatImg.click(); 
	}
	
	public void sendChatMessage(String chatMsg) {
		this.chatTextbox.clear(); 
		this.chatTextbox.sendKeys(chatMsg);
	}	
	
	public void clickSendChatMsgBtn() {
		this.sendChatMsgBtn.click(); 
	}
	
	public String verifyChatMessage() {
		String Message;
		Message = this.chatContent.getText();
		return Message;
	}
}
