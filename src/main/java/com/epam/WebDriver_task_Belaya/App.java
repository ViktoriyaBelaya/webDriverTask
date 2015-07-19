package com.epam.WebDriver_task_Belaya;

import java.util.ResourceBundle;

import com.epam.gmail.user_action.UserAction;


/**
 * Hello world!
 *
 */
public class App 
{
	
	
    public static void main( String[] args )
    {
    	  ResourceBundle resource = ResourceBundle.getBundle("com.epam.gmail.resource.users");
    	  String user1 = resource.getString("login_user1");
    	  String user2 = resource.getString("login_user2");
    	  String user3 = resource.getString("login_user3");
    	  String password = resource.getString("password");
    	UserAction action = new UserAction();
    	action.initBrowser();
    	action.loginUser(user2, password);
    	action.goToSetting();
    	action.addForwardUser(user3);
    	action.deleteAllCookies(); 
    	action.loginUser(user3, password);
    	action.confirmForwardUser();
    	action.deleteAllCookies();
    	action.loginUser(user2, password);
    	action.goToForwardPage();
    	action.clickRadioBtnFrwCopy();
    	action.createNewFilter(user1);
    	action.deleteAllCookies();
    	action.loginUser(user1, password);
    	action.sendMessageWithAttachFile(user2, 17);
    	action.sendMessage(user1);
    }
}
