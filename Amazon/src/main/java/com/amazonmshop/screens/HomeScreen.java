package com.amazonmshop.screens;

import static org.testng.Assert.assertTrue;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class HomeScreen {
	public static String prodDescription, prodPrice;

	public void loginFromHomeScreen(Client client, ReadProperties locators, ReadProperties configData)
			throws Exception {
		client.verifyElementFound("NATIVE", locators.getElement("Amazon_logo"), 0);
		client.click("NATIVE", locators.getElement("Menu_bar"), 0, 1);
		System.out.println("Select Menu bar from home page");
		client.click("NATIVE", locators.getElement("SignIn_menu"), 0, 1);
		System.out.println("Select Sign In option");
		login(client, locators, configData);
	}

	public void login(Client client, ReadProperties locators, ReadProperties configData) throws Exception {
		client.waitForElement("NATIVE", locators.getElement("Login_option"), 0, 10000);
		client.click("NATIVE", locators.getElement("Login_option"), 0, 1);
		client.elementSendText("NATIVE", locators.getElement("UserId_textfield"), 0, configData.getElement("UserId"));
		client.click("NATIVE", locators.getElement("Continue_button"), 0, 1);
		System.out.println("Click on Continue button in login page");
		client.waitForElement("NATIVE", locators.getElement("Password_textfield"), 0, 10000);
		client.elementSendText("NATIVE", locators.getElement("Password_textfield"), 0,
				configData.getElement("Password"));
		client.click("NATIVE", locators.getElement("Login_button"), 0, 1);
		System.out.println("Click on Login button");

		// verify login
		client.waitForElement("NATIVE", locators.getElement("Menu_bar"), 0, 10000);
		if (client.isElementFound("NATIVE", locators.getElement("Language_notification"))) {
			client.click("NATIVE", locators.getElement("Language_notification"), 0, 1);
		}
		client.click("NATIVE", locators.getElement("Menu_bar"), 0, 1);
		assertTrue(client.elementGetText("NATIVE", locators.getElement("SignIn_menu"), 0)
				.contains(configData.getElement("Username")));
		System.out.println("Login success");
		client.click("NATIVE", locators.getElement("SignIn_menu"), 0, 1);
	}

	/*
	 * Unable to add 65-inch tv to cart, because of Covid-19, there are some
	 * delivery restrictions. So searching for health drinks
	 */
	public void searchProduct(Client client, ReadProperties locators, String productName) throws Exception {

		client.waitForElement("NATIVE", locators.getElement("Search_textField"), 0, 10000);

		// Search from text field or from action bar, based on which option is available
		if (client.isElementFound("NATIVE", locators.getElement("Search_textField"))) {
			client.click("NATIVE", locators.getElement("Search_textField"), 0, 1);
		} else {
			client.click("NATIVE", locators.getElement("Search_actionBar"), 0, 1);
		}

		// Clear previous search if any
		if (client.isElementFound("NATIVE", locators.getElement("Clear_button")))
			client.click("NATIVE", locators.getElement("Clear_button"), 0, 1);

		client.sendText(productName);
		client.sendText("{ENTER}");
		System.out.println("Searching for " + productName);
		client.waitForElement("NATIVE", locators.getElement("Search_list"), 0, 10000);

		// Store details of 2nd item from the search list, and select the same
		client.swipeWhileNotFound("Down", 300, 1000, "NATIVE", locators.getElement("SearchItem_price"), 1, 500, 3,
				false);
		prodDescription = client.elementGetText("NATIVE", locators.getElement("SearchItem_title"), 1);
		prodPrice = client.elementGetText("NATIVE", locators.getElement("SearchItem_price"), 1);
		client.click("NATIVE", locators.getElement("Search_list"), 1, 1);
		System.out.println("Selected 2nd search item from search list");
		System.out.println("***********" + prodDescription + " " + prodPrice);
	}

}
