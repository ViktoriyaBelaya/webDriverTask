package com.epam.test_gmail;

import java.awt.AWTException;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.epam.gmail.utils.DriverUtils;

public class LetterFromUser1InSpamTest extends BaseTest {

	@Test
	public void checkLetterGoToSpam() {
		String subject = DriverUtils.getRandomString(10);
		String message = DriverUtils.getRandomString(100);
		userAction.loginUser(user1, password);
		userAction.sendMessage(user2, subject, message);
		userAction.deleteAllCookies();
		userAction.loginUser(user2, password);
		userAction.makeSpamLetter();
		userAction.deleteAllCookies();
		userAction.loginUser(user1, password);
		userAction.sendMessage(user2, subject, message);
		userAction.deleteAllCookies();
		userAction.loginUser(user2, password);
		userAction.viewSpamBox();
		Assert.assertTrue(userAction.isLetterSpam());
	}

	@AfterTest
	public void afterClass() {
		userAction.initBrowser();
		userAction.deleteAllCookies();
		userAction.loginUser(user2, password);
		userAction.makeAllMessegNoSpam();
		userAction.closeDriver();

	}

}
