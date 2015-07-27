package com.epam.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class MainPage extends Page {
	private final static String URL = "https://mail.google.com/mail";
	private final static String TOPIC_OF_THE_LETTER = "//div[@class='y6']/span/b[contains(text(),'%s')]";
	private static String emoticons = "//img[@class='CToWUd']";

	@FindBy(xpath = "//div[contains(text(),'НАПИСАТЬ')]")
	private WebElement buttonWriteMail;

	@FindBy(xpath = "//div[@class='Cr aqJ']/div[@class='G-Ni J-J5-Ji']/div")
	private WebElement buttonSettings;

	@FindBy(xpath = "//div[@role='menuitem']/div[contains(text(),'Настройки')]")
	private WebElement menuItemSettings;

	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#sent']")
	private WebElement letterInSend;

	@FindBy(xpath = "//img[@role = 'menu']")
	private WebElement buttonElse;

	@FindBy(xpath = "//div[@act = '9']/img")
	private WebElement spam;
	
	@FindBy(xpath = "//span[contains(text(),'Цепочка отмечена как спам.')]")
	private WebElement spamMessage;
	
	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		// TODO Auto-generated constructor stub
	}

	public void clickButtonWriteMail() {
		buttonWriteMail.click();
	}

	public void clickButtonSettings() {
		buttonSettings.click();
		menuItemSettings.click();
	}

	public void goToSendFolder() {
		letterInSend.click();
	}
	
	public boolean isMessageWithEmoticons(String subject) {

		while (DriverUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject))) {
			driver.navigate().refresh();
		}
		WebElement message = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));
		System.out.println("не нашел");
		message.click();
		System.out.println("click");
		if (DriverUtils.isElementPresent(driver, emoticons)) {
			if(driver.findElements(By.xpath(emoticons)).size() == MessagePage.getCountEmoticons()){
				System.out.println("ok");
				return true;
			}
		}
		return false;
	}
	
	public void isMessage(String subject) {

		while (DriverUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject))) {
			driver.navigate().refresh();
		}
		WebElement message = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));
		System.out.println("нашел");
		message.click();
		System.out.println("click");
	}
	
	public void markLetterAsSpam(WebDriver driver) {
		DriverUtils.waitElementVisible(driver, buttonElse, 100);
		buttonElse.click();
		spam.click();
		DriverUtils.waitElementVisible(driver, spamMessage, 150);
	}


	@Override
	public void openPage() {
		driver.navigate().to(URL);
	}

}
