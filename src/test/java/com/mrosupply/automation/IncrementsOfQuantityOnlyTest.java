package com.mrosupply.automation;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * IncrementsOfQuantityOnly class is created for test adding different quantity
 * of items in 'Shopping Cart' of 'MRO Supply' Web application and respective
 * validation message.
 *
 */

public class IncrementsOfQuantityOnlyTest extends TestBase {

	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {
			throw new SkipException("Runmode set to No");
		}
	}

	/**
	 * This test method will check Shopping Cart functionality with - 1. The
	 * items quantity 20 in 'Shopping Cart' of 'MRO Supply' Web application and
	 * respective validation message ( Minimum Order Quantity for Item #2265610
	 * is 25 )
	 * 
	 * 2. The items quantity 32 in 'Shopping Cart' of 'MRO Supply' Web
	 * application and respective validation message ( This product can only be
	 * purchased in increments of 25 )
	 * 
	 * 3. The items quantity 25 in 'Shopping Cart' & click on 'Check Out' of
	 * 'MRO Supply' Web application and page should navigate to ‘Enter your
	 * location and contact information’.
	 *
	 * @note: Test method is failing as no validation message is displayed on UI
	 */

	@Test
	public void incrementsOfQuantityOnlyTest() {

		try {
			capabilities.setCapability("name", "IncrementsOfQuantityOnly");
			// Search for the Product with the Product ID 2265610
			Reporter.log("Searching for the Product with the ID 2265610 ");
			// Enter Product ID 2265610 in the search box
			if (!WebUIAutomation.setText("TXT_SearchBox_HEADER", "2265610")) {
				Assert.fail("Text has not been entered successfully in search box");
			}

			// Click on Search
			Reporter.log("Click On Search ");
			if (!WebUIAutomation.clickObj("IMG_SearchIcon_HEADER")) {
				Assert.fail("Search Link not been clicked successfully");
			}

			// Wait for 3sec
			Thread.sleep(3000);

			// Click on add to cart
			Reporter.log("Click on Add to Cart");
			if (!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_SEARCH")) {
				Assert.fail("First item has not been clicked successfully on Search result page");
			}

			// Click on View Cart
			Reporter.log("Click on View Cart");
			if (!WebUIAutomation.clickObj("LNK_ViewCart_HEADER")) {
				Assert.fail("View Cart to cart button has not been clicked successfully on header");
			}

			// Change item Quantity to 20
			Reporter.log("Change item qty to 20");
			if (!WebUIAutomation.setText("BTN_Qty_SEARCH", "20")) {
				Assert.fail("Qty has not been changed successfully");
			}

			// Click on Checkout button
			Reporter.log("Click on Checkout button");
			if (!WebUIAutomation.clickObj("BTN_CheckOut_SEARCH")) {
				Assert.fail("Checkout button has not been clicked successfully");
			}

			Thread.sleep(3000);

			// Verifying Attention message displays - ‘Minimum Order Quantity
			// for
			// Item #2265610 is 25 ’
			Reporter.log("Verifying Attention message displays - "
					+ "Minimum Order Quantity for Item #2265610 is 25");

			boolean condMin25 = WebUIAutomation
					.getObject("MSG_MinimumOrderQuantity_SEARCH").getText()
					.endsWith("Minimum Order Quantity for Item #2265610 is 25"); // Store
																					// the
																					// verification
																					// result
																					// into
																					// a
																					// boolean
																					// variable
																					// condMin25

			Assert.assertTrue(condMin25,
					"Minimum Order Quantity for Item #2265610 is 25"); // Verifying
																		// the
																		// Attention
																		// message

			// wait
			Thread.sleep(2000);

			// Change item qty to 32
			Reporter.log("Change item qty to 32");
			if(!WebUIAutomation.setText("BTN_Qty_SEARCH", "32")) { // Change quantity
				Assert.fail("Qty has not been changed to 32");												// to 32
			}
			// Click on Checkout button
			Reporter.log("Click on Checkout button");
			if (!WebUIAutomation.clickObj("BTN_CheckOut_SEARCH")) {
				Assert.fail("Checkout button has not been clicked successfully");
			}

			Thread.sleep(5000);

			// Verify Alert is displayed
			if (!WebUIAutomation.isObjPresent(
					"MSG_MinimumOrderQuantity_SEARCH", 10)) {
				Assert.fail("Alert Message is not displayed");
			}

			// Verifying Attention message displays: This product can only be
			// purchased in
			// increments of 25
			Reporter.log("Verifying Attention message displays:"
					+ "This product can only be purchased in increments of 25");
			boolean condonlyinIncrementof25 = WebUIAutomation
					.getObject("MSG_MinimumOrderQuantity_SEARCH")
					.getText()
					.endsWith(
							"This product can only be purchased in increments of 25"); // Store
																						// the
																						// verification
																						// result
																						// into
																						// a
																						// boolean
																						// variable
																						// condonlyinIncrementof25

			Assert.assertTrue(condonlyinIncrementof25,
					"This product can only be purchased in increments of 25");

			// Change item qty to 25
			Reporter.log("Change qty to 25");
			if(!WebUIAutomation.setText("BTN_Qty_SEARCH", "25")){ // Change quantity
				Assert.fail("Qty has not been changed to 25");												// to 25
			}
			// Storing the current window handle
			// String originalHandle = driver.getWindowHandle();

			// Click on 'Check out' button
			Reporter.log("Click on check out button");
			if(!WebUIAutomation.clickObj("BTN_CheckOut_SEARCH")){
				Assert.fail("Checkout button has not been clicked successfully");
			}

			// wait
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Helper.getLocator(("TXT_EnterLocationNCOntactInfo_SEARCH"),OR)));
			// Verify that it moves forward to step ‘Enter your location and
			// contact
			// information’.
			Reporter.log("Verifying that it moves forward to ‘Enter your location"
					+ " and contact information’ cart step.");

			boolean condEnterLocationNcontactinfo = WebUIAutomation
					.isObjPresent("TXT_EnterLocationNCOntactInfo_SEARCH", 10);
			// Checking Enter your location and contact information is displayed
			// or not & store the status into a boolean variable
			// condEnterLocationNcontactinfo

			Assert.assertTrue(
					condEnterLocationNcontactinfo,
					"Its not leading to - "
							+ "‘Enter your location and contact information’ cart step"); // Verify
																							// that
																							// it
																							// moves
																							// forward
																							// to
																							// step
																							// ‘Enter
																							// your
																							// location
																							// and
																							// contact
																							// information’.

		} catch (Exception e) {
			Assert.assertTrue(false,"Caught Exception");
			e.printStackTrace();
		} 
	} 

}

/*
 * @AfterTest public void closeBrowser() {
 * 
 * driver.close(); }
 * 
 * }
 */
