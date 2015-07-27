package com.epam.test_gmail;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.gmail.utils.DriverUtils;

public class EmoticonsTest5 extends BaseTest {
	@Test
	public void sendMailWithEmoticons() {
		String subject = DriverUtils.getRandomString(10);
		String message = DriverUtils.getRandomString(100);
		userAction.loginUser(user1, password);
		userAction.sendMessageWithEmoticons(user3, subject, message);
		userAction.deleteAllCookies();
		userAction.loginUser(user3, password);
		Assert.assertTrue(userAction.isMessageWithEmoticons(subject));
	}
}
