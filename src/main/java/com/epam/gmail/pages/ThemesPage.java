package com.epam.gmail.pages;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class ThemesPage extends Page {
	private final static String URL = "https://mail.google.com/mail/u/1/#settings/oldthemes";
	private final static String errorMessage = "//div[contains(text(),'Формат выбранного файла')]";

	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/?tm=1#settings/themes']")
	private WebElement linkSettingThemes;

	@FindBy(xpath = "//div[@role='button'][contains(text(),'Мои фото')]")
	private WebElement buttonMyFhoto;

	@FindBy(xpath = "//div[contains(text(),'Загрузка фото')]")
	private WebElement uploadFhoto;

	@FindBy(xpath = "//div[@role = 'button'][contains(text(),'Выберите файл на компьютере')]")
	private WebElement buttonChooseFhotoFromComputer;

	@FindBy(xpath = "//iframe[@class='KA-JQ']")
	private WebElement frameForChangePhoto;

	public ThemesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void clickLinkSettingThemes() {

		DriverUtils.waitElementVisible(driver, linkSettingThemes, 50);
		linkSettingThemes.click();
		System.out.println("not arrived 1");
	}

	public void goToUploadFhoto() {
		DriverUtils.waitElementVisible(driver, uploadFhoto, 50);
		uploadFhoto.click();
		System.out.println("click uploadFhoto");
	}

	public void clickButtonChooseFhotoFromCompute() {
		DriverUtils.waitElementVisible(driver, buttonChooseFhotoFromComputer,
				50);
		buttonChooseFhotoFromComputer.click();
		System.out.println("click buttonChooseFhotoFromComputer");
	}

	public void clickButtonMyFhoto() {
		//driver.switchTo().frame(frameForChangePhoto);
		System.out.println("not arrived2");
		DriverUtils.waitElementVisible(driver,buttonMyFhoto,100);
		buttonMyFhoto.click();
		System.out.println("not arrived3");
		driver.switchTo().frame(frameForChangePhoto);
		System.out.println("not arrived 4");
	}

	public void uploadPhotoFromComputer(long size) throws IOException,
			AWTException {
		File file = DriverUtils.getRandomFile(size);
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		DriverUtils.javaRobotForAttachFile(ss);
	}

	public boolean isErrorMessageFormat() {
		return DriverUtils.isElementPresent(driver, errorMessage);
	}

	@Override
	public void openPage() {
		driver.navigate().to(URL);
	}

}
