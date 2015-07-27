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

		@AfterClass(description = "Delete generated file")
		public void afterClass() {
			DriverUtils.deleteFile(new File("file.txt"));
			System.out.println("Generated file was deleted");
		}
		
		@Test(description = "Themes")
		public void changeTheme() {
			userAction.loginUser(user2, password);
			userAction.goToSettingMenu();
			userAction.goToThemesPage();
			userAction.UploadPhotos(1024 * 1024 * 26);
			Assert.assertTrue(userAction.isErrorMassageAboutThemes());
		}
	}
}
