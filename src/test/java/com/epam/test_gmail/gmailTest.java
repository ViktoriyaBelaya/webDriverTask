package com.epam.test_gmail;

import java.util.ResourceBundle;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.gmail.user_action.UserAction;

public abstract class gmailTest {
	protected static UserAction action;
	protected ResourceBundle resource = ResourceBundle.getBundle("users");
	protected String user1 = resource.getString("login_user1");
	protected String user2 = resource.getString("login_user2");
	protected String user3 = resource.getString("login_user3");
	protected String password1 = resource.getString("password");
	protected String password2 = resource.getString("password");
	protected String password3 = resource.getString("password");
	
	@BeforeClass(description = "Init Browser")
	public void setUp() {
		action = new UserAction();
		action.initBrowser();
		
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		action.closeDriver();

	}
}
