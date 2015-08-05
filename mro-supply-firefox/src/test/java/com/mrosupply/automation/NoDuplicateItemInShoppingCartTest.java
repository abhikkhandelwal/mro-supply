package com.mrosupply.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NoDuplicateItemInShoppingCartTest extends TestBase{
	
	@BeforeTest	
	public void doIHaveToSkip(){
		
		if(!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			
			throw new SkipException("Runmode set to No");
		}
	}
	@Test
	public void testNoDuplicateItemInShoppingCart() {
		
		try{																					// try-catch block starts
			removeAllItemsFromCart();
			// Navigate to brands link
			Reporter.log("Navigate to brands link");
			if(!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")){
				Assert.fail("Brand link has not been successfully clicked");
			}
									
			
			// Click Acme link
			Reporter.log("Click Acme link");
			if(!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")){
				Assert.fail("Acme link has not been successfully clicked");
			}
			
			// Check Add to Cart button is displayed or not
			Assert.assertTrue(WebUIAutomation.isObjPresent("LNK_FirstItemAddToCart_ACMEBRAND", 10), "Add to Cart button isn't displayed on UI");
			
			// Add to cart 
			Reporter.log("Add to cart");
			if(!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")) {
				Assert.fail("Add to cart button has not been successfully clicked");
			}
			
			
			// Wait for 2sec
			Thread.sleep(2000);
			
			
			// Verify that 'Product added to your cart' message displays
			Reporter.log("Verify that 'Product added to your cart' message"
					+ " displays");
			
			String message = getObject("MSG_ProductAddedToCart_ACMEBRAND")
					.getText();																	// Store the confirmation message for added Product into cart
			
			Assert.assertEquals(message, "Product added to your cart"
					, "Confirmation message doesn't matched");									// Verify the confirmation message after adding items to cart
			
			// Refresh page so that add to cart icon gets to previous state.
			Reporter.log("Refresh page so that add to cart icon gets to previous"
					+ " state");
			driver.navigate().refresh();														// refresh the page
			
		
			// Navigate to brands link
			Reporter.log("Navigate to brands link");
			if(!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")){
			Assert.fail("Brand link has not been successfully clicked");	
			}
									
			
			// Click Acme link
			Reporter.log("Click Acme link");
			if(!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")){
			Assert.fail("Acme link has not been successfully clicked");
			}
						
			
			// Add to cart 
			Reporter.log("Add to cart");
			if(!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")){
			Assert.fail("Add to cart button has not been successfully clicked");
			}
			// Wait for 2sec
			Thread.sleep(2000);
			
			
			// Verify that ‘This product already in your cart’ message displays			
			String nextMessage = getObject("MSG_ProductAddedToCart_ACMEBRAND")
					.getText();																	// Store the validation message into a String variable nextMessage
			
			Assert.assertTrue(nextMessage
					.equalsIgnoreCase("This product already in your cart")
						, "'This product already in your cart' message is "
								+ "not displayed");												// Verify proper validation message is displayed for adding duplicate product or not
					
		} catch(Exception e){
			Assert.assertTrue(false,"Caught Exception");
			e.printStackTrace();
		}																						// End of try-catch block
		
	}																							// end of test method testNoDuplicateItemInShoppingCart()


}
