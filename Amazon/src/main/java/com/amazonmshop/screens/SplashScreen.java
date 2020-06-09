package com.amazonmshop.screens;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class SplashScreen {
	HomeScreen homeScreen = new HomeScreen();

	public void loginFromSplashScreen(Client client, ReadProperties locators, ReadProperties configData)
			throws Exception {
		client.waitForElement("NATIVE", locators.getElement("SignIn_button"), 0, 10000);
		client.verifyElementFound("NATIVE", locators.getElement("Amazon_SplashLogo"), 0);
		client.swipeWhileNotFound("DOwn", 300, 1000, "NATIVE", locators.getElement("SignIn_button"), 0, 500, 2, false);
		client.click("NATIVE", locators.getElement("SignIn_button"), 0, 1);
		System.out.println("Select Sign In option");

		homeScreen.login(client, locators, configData);
	}

}
