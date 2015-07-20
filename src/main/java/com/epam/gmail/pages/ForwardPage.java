package com.epam.gmail.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class ForwardPage extends Page {

	private final static String URL = "https://mail.google.com/mail/#settings/fwdandpop";

	@FindBy(xpath = "//input[@act='add']")
	private WebElement addForwardUser;

	@FindBy(xpath = "//div[contains(text(),'Введите новый адрес пересылки:')]//input[@type='text']")
	private WebElement inputForwardUser;

	@FindBy(xpath = "//button[contains(text(),'След.')]")
	private WebElement buttonAddForwardUserNext;

	@FindBy(xpath = "//button[@name='ok']")
	private WebElement buttonOK;

	@FindBy(xpath = "//input[@value='Продолжить']")
	private WebElement buttonAccept;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='ok']")
	// button[@name = 'ok']
	private WebElement buttonConfirmOk;

	@FindBy(xpath = "//input[@value='Продолжить']")
	private WebElement buttonProceedForConfirmAdress;

	@FindBy(xpath = "//input[@name='sx_em'][@value='1']")
	private WebElement radioButtonForwardCopy;

	@FindBy(xpath = "//button[@guidedhelpid='save_changes_button']")
	private WebElement buttonSaveForwardChange;

	@FindBy(xpath = "//span[contains(text(),'Пересылать копии входящих сообщений на адреса')]/select")
	private WebElement buttonChooseActionForwardCopy;

	@FindBy(xpath = "//div[contains(text(),'Адрес пересылки vviikkttoorriiyyaa1@gmail.com удален.')]")
	private WebElement messageDeleteForwardUser;

	@FindBy(xpath = "//span[contains(text(),'Просмотреть настройки')]")
	private WebElement buttonViewSettings;

	@FindBy(xpath = ".//span//option[@value='2']")
	private WebElement buttonDeleteForwardUser;

	public ForwardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void clickOK() {
		buttonOK.click();
	}

	public void saveForwardChange() {
		buttonSaveForwardChange.click();
	}

	public void chooseActionForwardCopy() {
		buttonChooseActionForwardCopy.click();
	}

	public void deleteForwardUser() {
		buttonDeleteForwardUser.click();
		clickOK();
		DriverUtils.waitElementVisible(driver, messageDeleteForwardUser, 100);

	}

	public void cliclViewSettingsAfterSave() {
		DriverUtils.waitElementVisible(driver, buttonViewSettings, 100);
		buttonViewSettings.click();
	}

	public void setForwardUser(String user) {
		DriverUtils.waitElementVisible(driver, addForwardUser, 100);
		addForwardUser.click();
		DriverUtils.waitElementVisible(driver, inputForwardUser, 100);
		inputForwardUser.sendKeys(user);
		String windowHandle = driver.getWindowHandle();
		buttonAddForwardUserNext.click();
		Set<String> listHandles = driver.getWindowHandles();
		for (String handle : listHandles) {
			if (!handle.equals(windowHandle)) {
				driver.switchTo().window(handle);
			}
		}
		DriverUtils.waitElementVisible(driver, buttonProceedForConfirmAdress,
				100);
		buttonProceedForConfirmAdress.click();
		driver.switchTo().window(windowHandle);
		buttonConfirmOk.click();

	}

	public void clickRadioButtonForwardCopy() {
		radioButtonForwardCopy.click();
	}

	@Override
	public void openPage() {
		driver.navigate().to(URL);

	}

}
