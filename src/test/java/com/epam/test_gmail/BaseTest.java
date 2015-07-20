package com.epam.test_gmail;

import java.util.ResourceBundle;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.gmail.user_action.UserAction;

public abstract class BaseTest {

	protected static UserAction userAction;
	protected ResourceBundle resource = ResourceBundle
			.getBundle("com.epam.gmail.resource.users");
	protected String user1 = resource.getString("login_user1");
	protected String user2 = resource.getString("login_user2");
	protected String user3 = resource.getString("login_user3");
	protected String password = resource.getString("password");

	@BeforeClass(description = "Init Browser")
	public void setUp() {
		userAction = new UserAction();
		userAction.initBrowser();
		userAction.deleteAllCookies();

	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		userAction.closeDriver();

	}
}
