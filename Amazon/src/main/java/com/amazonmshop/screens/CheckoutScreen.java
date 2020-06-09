package com.amazonmshop.screens;

import static org.testng.Assert.assertTrue;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class CheckoutScreen extends HomeScreen {

	public void verifyProductDetails(Client client, ReadProperties locators) throws Exception {
		client.waitForElement("NATIVE", locators.getElement("ProceedToBuy_button"), 0, 10000);

		// No specific locator id is available for product description and price. So,
		// tried maximum to verify description
		String description = client.elementGetText("NATIVE", locators.getElement("Product_description"), 0);
		assertTrue(HomeScreen.prodDescription.contains(description), "Product description validated");
		System.out.println("Prodct details validated in Checkout screen");

	}
}
