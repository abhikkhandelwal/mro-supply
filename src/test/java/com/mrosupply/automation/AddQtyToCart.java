package com.mrosupply.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * AddQtyToCart class is created to test the functionality of adding the
 * different quantity items in 'Shopping Cart' of 'MRO Supply' Web application
 * and respective validation message.
 *
 * 
 * 
 * @note: for item qty 1 application modifies the qty value to 5, so script will
 *        fail for item qty 100000 application can't take the qty value,maximum
 *        limit of qty is 9999, so script will fail
 */

public class AddQtyToCart extends TestBase {

	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}

	/**
	 * This test method will check Shopping Cart functionality - 1. Cart should
	 * contents matching recorded items id and qty
	 * 
	 * 2. Cart items should be empty after removing items from it.
	 *
	 * @param quantity
	 *            , it will store the items quantity from excel sheet
	 */

	@Test(dataProvider = "getData")
	public void testAddQtyToCart(String quantity) {

		try {
			capabilities.setCapability("name", "AddQtyToCartTest");

			// Create WebDriverWait instance
			WebDriverWait wait = new WebDriverWait(driver, 15);

			// Wait for brands link to be appear on the screen
			wait.until(ExpectedConditions.elementToBeClickable(WebUIAutomation
					.getObject("LNK_Brand_HOMEPAGE")));

			// Start of the Test
			System.out.println("Add qty to cart test has been started");

			// Navigate to brands link
			Reporter.log("Navigate to brands link");
			if (!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")) {
				Assert.fail("Brand link has not been clicked successfully and "
						+ "subsequent page has not been displayed.");
			}

			// Wait for Acme link to be appear on the screen
			WebElement objProduct = wait.until(ExpectedConditions
					.elementToBeClickable(WebUIAutomation
							.getObject("LNK_Acme_BRANDSPAGE")));

			// Click Acme link
			Reporter.log("Click Acme link");
			objProduct.click();

			// Record first item’s item id
			Reporter.log("Record first item’s item id");
			String itemId = getObject("LNK_FirstProductID_ACMEBRAND").getText(); // Store
																					// first
																					// item
																					// id
																					// into
																					// a
																					// String
																					// variable
																					// itemId

			// Check QTY input field is displayed or not
			Reporter.log("Check QTY input field is displayed or not");
			Assert.assertTrue(WebUIAutomation.isObjPresent(
					"TXT_FirstItemQty_ACMEBRAND", 10),
					"1st QTY input field isn't displayed on screen");

			// Add quantity for the first item
			Reporter.log("Add quantity for the first item");
			if (!WebUIAutomation
					.setText("TXT_FirstItemQty_ACMEBRAND", quantity)) {
				Assert.fail("Text has not been entered into quanity text field");
			}

			// Check Add to Cart button is displayed on UI or not
			Assert.assertTrue(WebUIAutomation.isObjPresent(
					"LNK_FirstItemAddToCart_ACMEBRAND", 10),
					"Add to Cart button isn't displayed on UI");

			// Add to cart
			Reporter.log("Add to cart");
			if (!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")) {
				Assert.fail("Add to cart link has not been clicked successfully and "
						+ "subsequent page has not been displayed.");
			}

			// Wait for 5sec
			Thread.sleep(5000);

			// Click on Cart
			Reporter.log("Click on Cart");
			if (!WebUIAutomation.clickObj("LNK_ViewCart_HEADER")) {
				Assert.fail("View to cart link has not been clicked successfully and "
						+ "subsequent page has not been displayed.");
			}

			// Verify cart contents matching recorded items id and qty of 5
			Reporter.log("Verify cart contents matching recorded items id");

			String cartItemId = getObject("LNK_FirstProductID_SHOPPINGCART")
					.getText(); // Store cart's Item id into a String variable
								// cartItemId

			Assert.assertTrue(
					itemId.equalsIgnoreCase(cartItemId),
					("Item ID of the product which we have added in the cart"
							+ " and the Item ID of the product which is displayed"
							+ " in the cart are not matching")); // Verify cart
																	// contents
																	// matching
																	// recorded
																	// items id

			// Verify cart contents matching items quantity
			Reporter.log("Verify cart contents matching quantity");

			String quantityForItemInShoppingCart = getObject(
					"TXT_FirstProductQuantity_SHOPPINGCART").getAttribute(
					"value"); // Store cart's Item quantity into a String
								// variable quantityForItemInShoppingCart

			Assert.assertTrue(
					quantity.equalsIgnoreCase(quantityForItemInShoppingCart),
					"Value inside quantity is not matching to the number of "
							+ "quantity added in the cart"); // Verify cart
																// contents
																// matching
																// items
																// quantity

			// Click on the first product Remove button
			Reporter.log("Remove from cart");
			if (!WebUIAutomation
					.clickObj("BTN_RemoveFirstProductFromCart_SHOPPINGCART")) {// Remove
																				// items
																				// from
																				// cart
				Assert.fail("Remove from cart button has not been clicked successfully and "
						+ "subsequent page has not been displayed.");
			}

			// Wait for 5sec
			Thread.sleep(5000);

			// Verify that cart is empty
			Reporter.log("Verify that cart is empty");
			
			WebUIAutomation.isObjPresent("TXT_EmptyCartMessage_SHOPPINGCART", 30);
			String emptyCartMessage = getObject(
					"TXT_EmptyCartMessage_SHOPPINGCART").getText();

			Assert.assertTrue(
					emptyCartMessage.equalsIgnoreCase("(Your Cart is Empty)"),
					"Cart Empty message is not displayed"); // Verify Cart is
															// empty or not

		} catch (Exception e) {
			Assert.assertTrue(false, "Caught Exception\n " + e.getMessage());
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getData() {

		return WebUIAutomation.getData("AddQtyToCart");
	}

	/*
	 * 
	 * @AfterTest public void closeBrowser(){
	 * 
	 * //driver.close(); }
	 */

}
