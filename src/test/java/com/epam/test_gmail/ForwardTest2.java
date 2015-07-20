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
		boolean status = true;
		String subject = DriverUtils.getRandomString(10);
		String message = DriverUtils.getRandomString(100);
		String subjectWithAttach = DriverUtils.getRandomString(10);
		String messageWithAttach = DriverUtils.getRandomString(100);
		//DriverUtils.deleteFile(new File("file.txt"));
		userAction.loginUser(user2, password);
		userAction.goToSetting();
		userAction.addForwardUser(user3);
		userAction.deleteAllCookies();
		System.out.println("step 1");
		userAction.loginUser(user3, password);
		userAction.confirmForwardUser();
		userAction.deleteAllCookies();
		System.out.println("step 2");
		userAction.loginUser(user2, password);
		userAction.goToForwardPage();
		System.out.println("step 3");
		userAction.clickRadioBtnFrwCopy();
		System.out.println("step 4");
		userAction.createNewFilter(user1);
		System.out.println("step 5");
		userAction.deleteAllCookies();
		System.out.println("step 6");
		userAction.loginUser(user1, password);
		
		userAction.sendMessageWithAttachFile(user2, 17,subjectWithAttach, messageWithAttach);
		userAction.sendMessage(user2,subject, message);
		userAction.deleteAllCookies();

		userAction.loginUser(user2, password);
		
		userAction.goToTrashPage();

		if (userAction.isLetterFromUser1WithAttachInTrash(subjectWithAttach)) {
			System.out.println("Success. There is letter from user1 with attach and mark as important in trash");
		} else {
			status = false;
			System.out.println("Error. There is letter from user1 with attach and mark as important in trash");
		}

		userAction.goToMail();
		if (userAction.isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(messageWithAttach)) {
			System.out.println("Success. There is letter from user1 without attach and not mark as important in inbox");
		} else {
			status = false;
			System.out.println("Error.There is letter from user1 without attach and not mark as important in inbox");
		}


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
