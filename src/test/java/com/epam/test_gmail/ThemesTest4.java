package com.epam.test_gmail;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.epam.gmail.utils.DriverUtils;

public class ThemesTest4 extends BaseTest {

	public class BigAttachFileTest3 extends BaseTest {


		@Test(description = "Themes")
		public void changeTheme() {
			userAction.loginUser(user1, password);
			userAction.goToSettingMenu();
			userAction.goToThemesPage();
			userAction.UploadPhotos();
		}
	}
}
