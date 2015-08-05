package com.mrosupply.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



/**
 * This test method is created for testing -
 * 		2. Checking the functionality deleting Items from the cart 
 */

public class DeleteItemFromShoppingCartTest extends TestBase{
	
	@BeforeTest
	public void doIHaveToSkip(){
		
		if(!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			
			throw new SkipException("Runmode set to No");
		}
	}
	
	@Test
	public void testDeleteItemFromShoppingCartTest() {
		
		try {																						
			System.out.println("Delete item from shoping cart test has been started");
			// Navigate to Brands Link 
			Reporter.log("Navigate to Brands link ");
			if(!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")) {
				Assert.fail("Brand link has not been clicked successfully and"
						+ "subsequent page hasn't been displayed.");
			}
			
			// Click 'Acme' link 
			Reporter.log("Click Acme link");
			if(!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")) {
				Assert.fail("Acme link has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}
			
			// Record First item’s item id
			Reporter.log("Record First item’s item id");
			
			
			String itemId1 = getObject("LNK_FirstProductID_ACMEBRAND").getText();				// Storing First Item ID for comparison	into a String variable itemId2
			
			// Check Add to cart button is displayed or not
			Reporter.log("");
			Assert.assertTrue(WebUIAutomation.isObjPresent("LNK_FirstItemAddToCart_ACMEBRAND", 30), "Unable to locate Add to cart button on the UI");
			
			// Add to cart 
			Reporter.log("Add to cart");
			if(!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")) {
				Assert.fail("Add to cart button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}
			
			
			// Wait for 5sec
			Thread.sleep(5000);
			
			
			// Click on Cart
			Reporter.log("Click on Cart");
			WebUIAutomation.clickObj("LNK_ViewCart_HEADER");
			
			
			// Verify cart contents matching recorded items id
			Reporter.log("Verify cart contents matching recorded items id");
			
			String cartItemId1 = getObject("LNK_FirstProductID_SHOPPINGCART").getText();		// Storing Item ID  from cart for comparison	into a String variable cartItemId2	
			
			Assert.assertTrue(itemId1.equalsIgnoreCase(cartItemId1)
					, "Item ID of the product which we have added in the cart and the "
							+ "Item ID of the product which is displayed in the cart are"
								+ " not matching");												// Verify item ids are matched  with search item id

			
			// Remove from cart
			Reporter.log("Remove from cart");
			WebUIAutomation.clickObj("BTN_RemoveFirstProductFromCart_SHOPPINGCART");
			
			Thread.sleep(5000);
			
			
			// Verify that cart content doesn’t match with recorded item id
			Reporter.log(" Verifying that cart is empty or not");
			boolean cond = WebUIAutomation.isElementPresent("BTN_RemoveFirstProductFromCart_SHOPPINGCART");
			Assert.assertFalse(cond,"Cart is not empty");
			}
		catch(Exception e){
			Assert.assertTrue(false,"Caught Exception");
			e.printStackTrace();
		}																				
	}

}
