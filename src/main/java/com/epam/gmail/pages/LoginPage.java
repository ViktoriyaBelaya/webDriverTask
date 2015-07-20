package com.epam.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.DriverUtils;

public class LoginPage extends Page {

	private static final String URL = "http://www.gmail.com";

	@FindBy(xpath = "id('Email')")
	private WebElement loginInput;

	@FindBy(xpath = "id('next')")
	private WebElement buttonNext;

	@FindBy(xpath = "id('Passwd')")
	private WebElement passwordInput;

	@FindBy(xpath = "id('signIn')")
	private WebElement buttonSingIn;

	@FindBy(xpath = "//input[@id='PersistentCookie']")
	private WebElement checkButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		// TODO Auto-generated constructor stub
	}

	public void loginUser(String login, String password) {
		loginInput.sendKeys(login);
		buttonNext.click();
		DriverUtils.waitElementVisible(driver, passwordInput, 150);
		passwordInput.sendKeys(password);
		// checkButton.click();
		buttonSingIn.click();
	}

	@Override
	public void openPage() {
		driver.navigate().to(URL);
		;
	}

}
