package com.epam.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class SettingsPage extends Page{
	
	private final static String URL = "https://mail.google.com/mail/#settings/general";

	
	@FindBy(xpath = "//a[contains(text(),'Фильтры')]")
	private WebElement buttonFilter;
	
	@FindBy(xpath = "//a[contains(text(),'Пересылка и POP/IMAP')]")
	private WebElement buttonForward;
	
	@FindBy(xpath = "//button[@name='ok']")
	private WebElement buttonOK;

	public SettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void goToForwardpage(){
		buttonForward.click();
	}
	
	public void goToFilter(){
		buttonFilter.click();
		
	}
	
	public void clickOK(){
		buttonOK.click();
	}
	
	@Override
	public void openPage() {
		driver.navigate().to(URL);
		
	}

}
