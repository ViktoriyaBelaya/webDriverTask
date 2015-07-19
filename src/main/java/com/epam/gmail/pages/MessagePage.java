package com.epam.gmail.pages;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class MessagePage extends Page {
	
	private final static String URL = "https://mail.google.com/mail";
	private final static String LOADING_BAR_WHEN_ATTACHED_FILE = "//div[@class='dR']";
	
	@FindBy(xpath = "//span[contains(text(),'Просмотреть настройки')]")
	private WebElement buttonViewSettings;
	
	@FindBy(xpath = "//textarea[@name = 'to']")
	private WebElement toInput;
	
	@FindBy(xpath = "//input[@placeholder='Тема']")
	private WebElement subjectInput;
	
	@FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
	private WebElement buttonSendMessage;
	
	@FindBy(xpath = "//div[@aria-label='Тело письма']")
	private WebElement bodyMail;
	
	@FindBy(xpath = "//div[@class='yW']/span[@class='zF'][ancestor::div[@class='ae4 aDM']]")
	private WebElement newMessage;
	
	@FindBy(xpath = "//img[@role = 'menu']")
	private WebElement buttonElse;
	
	@FindBy(xpath = "//div[@act = '9']/img")
	private WebElement spam;
	
	@FindBy(xpath = "//span[contains(text(),'Цепочка отмечена как спам.')]")
	private WebElement spamMessage;
	
	@FindBy(xpath = "//a[contains(text(),'https://isolated.mail.google.')]")
	private WebElement linkConfirmForward;
	
	@FindBy(xpath = "//div[contains(text(),'Письмо отправлено.')]")
	private WebElement messageLetterSend;
	
	@FindBy(xpath = "//div[@class='a1 aaA aMZ']")
	private WebElement buttonForAttachFile;
	
	@FindBy(xpath = "//span[@role='checkbox']/div[@role='presentation']")
	private WebElement checkSelectAll;
	
	@FindBy(xpath = "//div[@role='button'][@data-tooltip='Удалить']")
	private WebElement buttonDelete;
	
	@FindBy(xpath = "//span[contains(text(),'Цепочек, отправленных в корзину:')]")
	private WebElement messageDelete;
	
	@FindBy(xpath = "//button[@name='ok']")
	private WebElement buttonOK;
	
	public MessagePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		// TODO Auto-generated constructor stub
	}
	public void clickOK(){
		buttonOK.click();
	}
	
	public void selectAll(){
		checkSelectAll.click();
	}
	
	public void clickDeleteButtonMessage(){
		buttonDelete.click();
		DriverUtils.waitElementVisible(driver,messageDelete , 30);	
	}
	
	public void clickDeleteButtonSendMessage(){
		buttonDelete.click();
		DriverUtils.clickOkAlert(driver);
		DriverUtils.waitElementVisible(driver,messageDelete , 30);	
	}
	
	public void writeMassege(WebDriver driver,String user){
		toInput.sendKeys(user);	
		DriverUtils.waitElementVisible(driver,subjectInput , 30);		
	}
	
	public void sendMessage(){
		buttonSendMessage.click();
		DriverUtils.waitElementVisible(driver,messageLetterSend , 30);
	}
	
	
	public void enterSubjectAndBobyOfLetter(String subject, String message) {
		subjectInput.sendKeys(subject);
		bodyMail.sendKeys(message);
	}
		
	public void markLetterAsSpam(WebDriver driver){
		DriverUtils.waitElementVisible(driver, newMessage, 60);
		newMessage.click();
		DriverUtils.waitElementVisible(driver, buttonElse, 100);
		buttonElse.click();
		spam.click();
		DriverUtils.waitElementVisible(driver, spamMessage, 100);
	}
	
	public void confirmForwardFromUser(){
		String parentHandle = driver.getWindowHandle();
		String childHandle = "";
		newMessage.click();
		linkConfirmForward.click();
		Set<String> listHadles = driver.getWindowHandles();
		for(String handle:listHadles){
			if(!handle.equals(parentHandle)){
				driver.switchTo().window(handle);
				childHandle = handle;
			}
		}
		driver.switchTo().window(childHandle).close();
		driver.switchTo().window(parentHandle);
	}
	
	public void attachFile(long size) throws IOException, AWTException {
		buttonForAttachFile.click();
		File file = DriverUtils.getRandomFile(size);
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		DriverUtils.javaRobotForAttachFile(ss);
		if (DriverUtils.isElementPresent(driver, LOADING_BAR_WHEN_ATTACHED_FILE)) {
			DriverUtils.waitForElementInvisibility(driver,
					LOADING_BAR_WHEN_ATTACHED_FILE, 120);
		}

	}
	@Override
	public void openPage() {
		driver.navigate().to(URL);
		
	}
	
	
	

}
