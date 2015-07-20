package com.epam.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class TrashPage extends Page {

	private final static String BASE_URL = "https://mail.google.com/mail/#trash";
	private final static String INFO_ABOUT_IMPORTANT = "//span[contains(text(),'Отмечено как важное')]";
	private final static String TOPIC_OF_THE_LETTER = "//div[@class='y6']/span/b[contains(text(),'%s')]";

	private final static String IMAGE_ATTACHMENT = "//img[@alt='Прикрепленные файлы']";
	@FindBy(xpath = "//div[@class='yW']/span[@class='zF'][ancestor::div[@class='ae4 UI UJ']]")
	private WebElement newLetter;

	@FindBy(xpath = "//img[@class='ajz']")
	private WebElement imgInfoAboutLetter;

	@FindBy(xpath = "//div[@class='ajB gt']")
	private WebElement infoAboutLetter;

	@FindBy(xpath = "//span[contains(text(),'Отмечено как важное')]")
	private WebElement infoMessageIsImportant;

	public TrashPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public boolean isMessageWithAttachInTrash(String subject) {

		while (DriverUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject))) {
			driver.navigate().refresh();
		}
		WebElement message = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));
		message.click();
		if (DriverUtils.isElementPresent(driver, IMAGE_ATTACHMENT)) {
			imgInfoAboutLetter.click();
			if (DriverUtils.isElementNotPresent(driver, INFO_ABOUT_IMPORTANT)) {
				return true;
			}
		}
		return false;
	}

	public boolean isMessageWithoutAttachInTrash(String subject) {

		while (DriverUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject))) {
			driver.navigate().refresh();
		}
		WebElement message = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));
		message.click();
		if (DriverUtils.isElementNotPresent(driver, IMAGE_ATTACHMENT)) {
			imgInfoAboutLetter.click();
			if (DriverUtils.isElementNotPresent(driver, INFO_ABOUT_IMPORTANT)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}
}
