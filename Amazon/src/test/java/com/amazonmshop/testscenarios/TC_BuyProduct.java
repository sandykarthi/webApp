package com.amazonmshop.testscenarios;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazonmshop.framework.ApplicationSetup;
import com.amazonmshop.framework.ReadProperties;
import com.amazonmshop.screens.CheckoutScreen;
import com.amazonmshop.screens.HomeScreen;
import com.amazonmshop.screens.ProductScreen;
import com.amazonmshop.screens.SplashScreen;
import com.experitest.client.Client;

@Test
public class TC_BuyProduct {
	public int port;
	public String projectBaseDirectory, filePath;
	protected Client client = null;
	public ReadProperties locators, configData;
	HomeScreen homeScreen = new HomeScreen();
	SplashScreen splashScreen = new SplashScreen();
	ApplicationSetup appSetup = new ApplicationSetup();
	ProductScreen productScreen = new ProductScreen();
	CheckoutScreen checkoutScreen = new CheckoutScreen();

	@BeforeTest
	public void setUp() throws NumberFormatException, Exception {
		// Get current working directory and load property files
		locators = new ReadProperties("locators.properties");
		configData = new ReadProperties("configDataFile.properties");
		filePath = configData.getElement("FilePath");

		// Initializing Seetest properties
		client = new Client(configData.getElement("Host"), Integer.parseInt(configData.getElement("Port")), true);
		client.setProjectBaseDirectory(configData.getElement("ProjectBaseDirectory"));

		/*
		 * Commented these lines, since application is not launching in instrumented
		 * mode. client.uninstall("com.amazon.mShop.android.shopping.test");
		 * client.install("com.amazon.mShop.android.shopping.test", true, false);
		 * client.launch("com.amazon.mShop.android.shopping.test", true, true);
		 * 
		 */
	}

	@BeforeMethod
	public void initializeTest() throws Exception {
		client.setReporter("html", filePath, "Login and buy a product");
		try {
			client.setDevice(configData.getElement("Device"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.deviceAction(configData.getElement("Orientation"));
	}

	/*
	 * Test steps in this testcase Login to application Search for a product Verify
	 * product detail in product screen Add product to cart Verify product detail in
	 * checkout screens
	 */

	@Parameters("productName")
	@Test(groups = { "seetest" })
	public void test(@Optional("soap") String productName) throws Exception {
		appSetup.launchApp(client, locators, configData);
		splashScreen.loginFromSplashScreen(client, locators, configData);
		homeScreen.searchProduct(client, locators, productName);
		productScreen.addToCart(client, locators);
		checkoutScreen.verifyProductDetails(client, locators);
	}

	@AfterMethod
	public void endTestCase() throws Exception {
		appSetup.EndTCandGenerateRepor(client, locators);
	}

	@AfterTest
	public void tearDown() {
		// Releases the client so that other clients can approach the agent in the near
		// future.
		client.releaseClient();
	}

}
