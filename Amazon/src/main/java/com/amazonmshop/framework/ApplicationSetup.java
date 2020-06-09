package com.amazonmshop.framework;

import com.experitest.client.Client;

public class ApplicationSetup {

	public void launchApp(Client client, ReadProperties locators, ReadProperties configData) throws Exception {

		// Launching app manually
		client.sendText("{HOME}");
		client.click("NATIVE", locators.getElement("App_icon"), 0, 1);

		// Logout if any user is already logged in
		client.waitForElement("NATIVE", locators.getElement("Amazon_SplashLogo"), 0, 10000);
		if (!client.isElementFound("NATIVE", locators.getElement("Amazon_SplashLogo"))) {
			logout(client, locators);
		}

	}

	public void EndTCandGenerateRepor(Client client, ReadProperties locators) throws Exception {
		if (client.isElementFound("NATIVE", locators.getElement("Menu_bar"))) {
			logout(client, locators);
		}
		client.closeAllApplications();
		client.sendText("{HOME}");
		client.generateReport(false);
	}

	public void logout(Client client, ReadProperties locators) throws Exception {
		if (!client.isElementFound("NATIVE", locators.getElement("Menu_list")))
			client.click("NATIVE", locators.getElement("Menu_bar"), 0, 1);
		client.elementSwipeWhileNotFound("NATIVE", locators.getElement("Menu_list"), "Down", 300, 1000, "NATIVE",
				locators.getElement("Settings_menu"), 0, 1000, 5, false);
		client.click("NATIVE", locators.getElement("Settings_menu"), 0, 1);
		client.click("NATIVE", locators.getElement("Signout_menu"), 0, 1);
		client.click("NATIVE", locators.getElement("Signout_button"), 0, 1);
		client.waitForElement("NATIVE", locators.getElement("SignIn_button"), 0, 10000);
		System.out.println("You are Signed out");
	}

}
