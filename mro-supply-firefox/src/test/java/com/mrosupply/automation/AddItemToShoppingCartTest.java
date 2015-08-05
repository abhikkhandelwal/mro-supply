package com.mrosupply.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 * 
 * ShoppingCartTest class is created for testing the shopping Cart functionality 
 *  of the 'MRO Supply' Web application and respective validation messages.
 *
 */

public class AddItemToShoppingCartTest extends TestBase {

	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}

	/**
	 * This test method is created for testing - 1. The functionality of adding
	 * Items to the cart
	 */

	@Test
	public void testAddItemToShoppingCartTest() {

		try {
			capabilities.setCapability("name", "AddItemToShoppingCartTest");
			// Navigate to Brands link
			Reporter.log("Navigate to brands Link ");
			if (!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")) {
				Assert.fail("Brand link has not been clicked successfully and"
						+ "subsequent page hasn't been displayed.");
			}

			// Start of the Test
			System.out
					.println("Add Item to Started Shopping Cart test has been started");

			// Click Acme link
			Reporter.log("Click Acme link");
			if (!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")) {
				Assert.fail("Acme link has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Record first item’s item id
			Reporter.log("Record first item’s item id");
			String itemId1 = getObject("LNK_FirstProductID_ACMEBRAND")
					.getText(); // Storing Item Id for comparison into a String
								// variable itemId

			// Check Add to cart button is displayed or not
			Reporter.log("Check Add to cart button is displayed or not");
			Assert.assertTrue(WebUIAutomation.isObjPresent(
					"LNK_FirstItemAddToCart_ACMEBRAND", 30),
					"Unable to locate Add to cart button on the UI");

			// Click on 'Add to cart' button
			Reporter.log("Click on 'Add to cart' button ");
			if (!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")) {
				Assert.fail("Add to cart button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Wait for 5sec
			Thread.sleep(5000);

			// Click on View Cart
			Reporter.log("Click on Cart");
			if (!WebUIAutomation.clickObj("LNK_ViewCart_HEADER")) {
				Assert.fail("View to cart button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Verify item Id of contents matching to recorded items id

			Reporter.log("Verify item id cart contents matching recorded items id");
			WebUIAutomation.isObjPresent("LNK_FirstProductID_SHOPPINGCART", 30);
			String cartItemId1 = getObject("LNK_FirstProductID_SHOPPINGCART")
					.getText(); // Storing Item Id form cart for Comparison into
								// a String variable cartItemId1

			Assert.assertTrue(itemId1.equalsIgnoreCase(cartItemId1),
					"Item ID of the product which we have added in the cart are not "
							+ "matching"); // Verify cart opens and item Id of
											// the cart items matching to
											// recorded items id or not

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Caught Exception");
		}

	}

}
