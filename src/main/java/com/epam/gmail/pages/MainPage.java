package com.epam.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
	private final static String URL = "https://mail.google.com/mail";

	@FindBy(xpath = "//div[contains(text(),'НАПИСАТЬ')]")
	private WebElement buttonWriteMail;
	
	@FindBy(xpath = "//div[@class='Cr aqJ']/div[@class='G-Ni J-J5-Ji']/div")
	private WebElement buttonSettings;
	
	@FindBy(xpath = "//div[@role='menuitem']/div[contains(text(),'Настройки')]")
	private WebElement menuItemSettings;
	
	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#sent']")
	private WebElement letterInSend;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public void clickButtonWriteMail(){
		buttonWriteMail.click();
	}
	public void clickButtonSettings(){
		buttonSettings.click();
		menuItemSettings.click();
	}
	public void goToSendFolder(){
		letterInSend.click();
	}
	
	

	@Override
	public void openPage() {
		driver.navigate().to(URL);
	}

}
