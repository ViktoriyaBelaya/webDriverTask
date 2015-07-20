package com.epam.gmail.user_action;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.gmail.pages.FilterPage;
import com.epam.gmail.pages.ForwardPage;
import com.epam.gmail.pages.LoginPage;
import com.epam.gmail.pages.MainPage;
import com.epam.gmail.pages.MessagePage;
import com.epam.gmail.pages.SettingsPage;
import com.epam.gmail.pages.SpamPage;
import com.epam.gmail.pages.TrashPage;

public class UserAction {
	WebDriver driver;

	public void initBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Browser started");
	}

	public void closeDriver() {
		driver.quit();
		System.out.println("Browser closed");
	}

	public void deleteAllCookies() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					if (!(d instanceof JavascriptExecutor)) {
						return true;
					}
					Object result = ((JavascriptExecutor) d)
							.executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
					if (result != null && result instanceof Boolean
							&& (Boolean) result) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			System.out.println("Page timeout!");
		}
		do {
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		} while (driver.manage().getCookieNamed("LSID") != null);

	}

	public void goToTrashPage() {
		TrashPage trashPage = new TrashPage(driver);
		trashPage.openPage();
		System.out.println("The trash page was opened");
	}

	public boolean isMessageWithAttachInTrash(String subject) {
		TrashPage trashPage = new TrashPage(driver);
		return trashPage.isMessageWithAttachInTrash(subject);
	}

	public boolean isMessageWithoutAttachInInboxAndNotMArkAsImportant(
			String subject) {
		MessagePage messagePage = new MessagePage(driver);
		messagePage.openPage();
		return messagePage.isMessageWithoutAttachInTrash(subject);

	}

	public void loginUser(String login, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.loginUser(login, password);
		System.out.println("Login performed");
	}

	public void makeAllMessegNoSpam() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.openPage();
		spamPage.selectAllSpamMessage();
		spamPage.makeAllMessageNoSpam();
	}

	public void sendMessage(String user, String subject, String message) {
		MainPage mainPage = new MainPage(driver);
		MessagePage sendMessage = new MessagePage(driver);
		sendMessage.openPage();
		mainPage.clickButtonWriteMail();
		sendMessage.writeMassege(driver, user);
		sendMessage.enterSubjectAndBobyOfLetter(subject, message);
		sendMessage.sendMessage();
		System.out.println("Message send");
	}

	public void sendMessageWithAttachFile(String user, long size,
			String subject, String message) {
		MainPage mainPage = new MainPage(driver);
		MessagePage sendMessage = new MessagePage(driver);
		sendMessage.openPage();
		mainPage.clickButtonWriteMail();
		sendMessage.writeMassege(driver, user);
		sendMessage.enterSubjectAndBobyOfLetter(subject, message);
		try {
			sendMessage.attachFile(size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sendMessage.isMessageSizeFilePresent())
			sendMessage.sendMessage();
		System.out.println("Message send");
	}

	public void createMessageWithBigFile(String user, long size,
			String subject, String message) {
		MainPage mainPage = new MainPage(driver);
		MessagePage sendMessage = new MessagePage(driver);
		sendMessage.openPage();
		mainPage.clickButtonWriteMail();
		sendMessage.writeMassege(driver, user);
		sendMessage.enterSubjectAndBobyOfLetter(subject, message);
		try {
			sendMessage.attachFile(size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToSendBox() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.goToSendFolder();
	}

	public void clickViewSettings() {
		ForwardPage page = new ForwardPage(driver);
		page.cliclViewSettingsAfterSave();
	}

	public void goToMail() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
	}

	public void deleteAllMessages() {
		MessagePage message = new MessagePage(driver);
		message.selectAll();
		message.clickDeleteButtonMessage();
	}

	public void deleteAllSendMessages() {
		MessagePage message = new MessagePage(driver);
		message.selectAll();
		message.clickDeleteButtonMessage();
	}

	public void makeSpamLetter() {
		MessagePage makespam = new MessagePage(driver);
		makespam.openPage();
		makespam.markLetterAsSpam(driver);
		System.out.println("message in spam");
	}

	public void viewSpamBox() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.openPage();
	}

	public boolean isLetterSpam() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.openPage();
		return spamPage.isLetterInSpam();
	}

	public void deleteForwardUser() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.deleteForwardUser();
	}

	public void clickRadioButtonForwardCopy() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.openPage();
		forwardPage.clickRadioButtonForwardCopy();
	}

	public void createNewFilter(String user) {
		SettingsPage settings = new SettingsPage(driver);
		settings.goToFilter();
		FilterPage filter = new FilterPage(driver);
		filter.clickCreateNewFilter();
		filter.enterInputFromFilter(user);
		filter.clickCheckboxHasAttachment();
		filter.clickLinkNextCreateFilter();
		filter.clickCheckboxDelete();
		filter.clickCheckboxMarkAsImportant();
		filter.clickCreateFilter();
	}

	public void goToSettingMenu() {

		MainPage mainPage = new MainPage(driver);
		mainPage.clickButtonSettings();
		SettingsPage settings = new SettingsPage(driver);
		settings.openPage();
	}

	public void goToSetting() {

		SettingsPage settings = new SettingsPage(driver);
		settings.openPage();
	}

	public void addForwardUser(String user) {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.openPage();
		forwardPage.setForwardUser(user);
		forwardPage.saveForwardChange();
	}

	public void clickButtonOkForward() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.clickOK();
	}

	public void confirmForwardUser() {
		MessagePage messagePage = new MessagePage(driver);
		messagePage.openPage();
		messagePage.confirmForwardFromUser();

	}

	public boolean isMessageBiggerThen25mb() {
		MessagePage messagePage = new MessagePage(driver);
		return messagePage.isMessageSizeFilePresent();
	}

	public void clickRadioBtnFrwCopy() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.clickRadioButtonForwardCopy();
		forwardPage.saveForwardChange();
		forwardPage.cliclViewSettingsAfterSave();
	}

	public void goToForwardPage() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.openPage();
	}

	public void goToForwardPageInSetting() {
		SettingsPage settings = new SettingsPage(driver);
		settings.goToForwardpage();
	}

	public void goToFilterPage() {
		FilterPage filter = new FilterPage(driver);
		filter.openPage();
	}

	public void deleteAllFilters() {
		FilterPage filter = new FilterPage(driver);
		filter.openPage();
		filter.selectAllFilters();
		filter.deleteAllFilters();
	}

}
