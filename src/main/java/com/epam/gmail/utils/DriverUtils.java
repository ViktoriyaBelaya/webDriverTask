package com.epam.gmail.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DriverUtils {
	
	private static final String CHARTS = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();
	private static final long IMPLICITY_WAIT = 15;

	public static boolean isElementPresent(WebDriver driver, String xpath) {
		driver.manage().timeouts()
				.implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(xpath)).size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isElementNotPresent(WebDriver driver, String xpath) {
		driver.manage().timeouts()
				.implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(xpath)).size() == 0) {
			return true;
		}
		return false;
	}
	
	public static void waitElementVisible(WebDriver driver,
			WebElement webElement, int time) {
		WebDriverWait driverWait = new WebDriverWait(driver, time);
		driverWait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void waitForElementInvisibility(WebDriver driver,
			String xpath, int time) {
		WebDriverWait driverWait = new WebDriverWait(driver, time);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(xpath)));

	}
	
	public static String getRandomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) 
			sb.append(CHARTS.charAt(random.nextInt(CHARTS.length())));
		return sb.toString();
	}
	
	public static File getRandomFile(long fileSize) throws IOException {
		File file = new File("file.txt");
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		rf.setLength(fileSize);
		rf.close();
		return file;
	}
	
	public static void deleteFile(File file) {
		file.delete();
	}
	public static void clickOkAlert(WebDriver driver){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void javaRobotForAttachFile(StringSelection ss)
			throws AWTException {

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
