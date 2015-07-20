package com.epam.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class SpamPage extends Page {

	private final static String SPAM_URL = "https://mail.google.com/mail/#spam";
	@FindBy(xpath = "//span[@email='oollggaa1992@gmail.com']")
	private WebElement spamMessage;

	@FindBy(xpath = "//button[@name='ok']")
	private WebElement buttonOK;

	@FindBy(xpath = "//span[@role='checkbox']/div[@role='presentation']")
	private WebElement checkSelectAll;

	@FindBy(xpath = "//div[@role='button'][contains(text(),'Не спам')]")
	private WebElement buttonNoSpam;

	@FindBy(xpath = "//span[contains(text(),'Отметка спама снята с цепочек')]")
	private WebElement infoMessageNoSpam;

	public SpamPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public boolean isLetterInSpam() {
		return DriverUtils.isElementPresent(driver,
				"//span[@email='oollggaa1992@gmail.com']");
	}

	public void clickOK() {
		buttonOK.click();
	}

	public void selectAllSpamMessage() {
		checkSelectAll.click();
	}

	public void makeAllMessageNoSpam() {
		DriverUtils.waitElementVisible(driver, buttonNoSpam, 80);
		buttonNoSpam.click();
		DriverUtils.waitElementVisible(driver, infoMessageNoSpam, 100);
	}

	@Override
	public void openPage() {
		driver.navigate().to(SPAM_URL);
	}

}
