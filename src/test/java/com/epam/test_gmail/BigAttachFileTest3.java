package com.epam.test_gmail;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.gmail.utils.DriverUtils;

public class BigAttachFileTest3 extends BaseTest {

	@AfterClass(description = "Delete generated file")
	public void afterClass() {
		DriverUtils.deleteFile(new File("file.txt"));
		System.out.println("Generated file was deleted");
	}

	@Test(description = "Attach file bigger then 25 mb")
	public void tryToAttachFileBigSize() throws IOException, AWTException {
		String subjectWithAttach = DriverUtils.getRandomString(10);
		String messageWithAttach = DriverUtils.getRandomString(100);
		userAction.loginUser(user1, password);
		userAction.createMessageWithBigFile(user2, 1024 * 1024 * 26,
				subjectWithAttach, messageWithAttach);
		Assert.assertTrue(userAction.isMessageBiggerThen25mb());
		userAction.deleteAllCookies();
	}
}
