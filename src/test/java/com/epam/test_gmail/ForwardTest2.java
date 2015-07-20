package com.epam.test_gmail;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.epam.gmail.utils.DriverUtils;

public class ForwardTest2 extends BaseTest {
	@Test
	public void forward() throws IOException, AWTException {
		String subject = DriverUtils.getRandomString(10);
		String message = DriverUtils.getRandomString(100);
		String subject1 = DriverUtils.getRandomString(10);
		String message1 = DriverUtils.getRandomString(100);
		DriverUtils.deleteFile(new File("file.txt"));
		userAction.loginUser(user2, password);
		userAction.goToSetting();
		userAction.addForwardUser(user3);
		userAction.deleteAllCookies();
		userAction.loginUser(user3, password);
		userAction.confirmForwardUser();
		userAction.deleteAllCookies();
		userAction.loginUser(user2, password);
		userAction.goToForwardPage();
		userAction.clickRadioBtnFrwCopy();
		userAction.createNewFilter(user1);
		userAction.deleteAllCookies();
		userAction.loginUser(user1, password);
		
		userAction.sendMessageWithAttachFile(user2, 17,subject1, message1);
		userAction.sendMessage(user2,subject, message);
		userAction.deleteAllCookies();

		userAction.loginUser(user2, password);

	}

	@AfterClass
	public void afterClass() throws AWTException {
		DriverUtils.deleteFile(new File("file.txt"));
		userAction.deleteAllCookies();
		userAction.closeDriver();
		userAction.initBrowser();
		userAction.loginUser(user2, password);
		userAction.goToForwardPage();
		userAction.deleteForwardUser();

	}
}
