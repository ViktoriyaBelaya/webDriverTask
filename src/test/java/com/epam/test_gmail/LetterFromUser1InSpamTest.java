package com.epam.test_gmail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LetterFromUser1InSpamTest extends BaseTest{
	
  @Test
  public void checkLetterGoToSpam() {
	  userAction.loginUser(user1, password);
	  userAction.sendMessage(user2);
	  userAction.deleteAllCookies();
	  userAction.loginUser(user2, password);
	  userAction.makeSpamLetter();
	  userAction.deleteAllCookies();
	  userAction.loginUser(user1, password);
	  userAction.sendMessage(user2);
	  userAction.deleteAllCookies();
	  userAction.loginUser(user2, password);
	  userAction.viewSpamBox();
	  Assert.assertTrue(userAction.isLetterSpam());
  }
}
