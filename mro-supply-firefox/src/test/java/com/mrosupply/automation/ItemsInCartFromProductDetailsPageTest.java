package com.mrosupply.automation;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ItemsInCartFromProductDetailsPageTest extends TestBase {

@BeforeTest	
	public void doIHaveToSkip(){
		
		if(!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			
			throw new SkipException("Runmode set to No");
		}
}

			@Test
			public void testItemsInCartFromProductDetailsPage() {
				
				try{																										
					// Wait for brands link to be appear on the screen
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.elementToBeClickable(WebUIAutomation.getObject("LNK_Brand_HOMEPAGE")));
					
					// Navigate to brands link
					Reporter.log("Navigate to brands link");
					if(!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")){
						Assert.fail("Brand link has not been clicked successfully");
					}
								
					
					// Wait for Acme link to be appear on the screen
					wait.until(ExpectedConditions.elementToBeClickable(WebUIAutomation.getObject("LNK_Acme_BRANDSPAGE")));
					
					
					// Click Acme link
					Reporter.log("Click Acme link");
					if(!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")){
					Assert.fail("Acme link has not been clicked successfully");	
					}
					
					
					
					// Record First item’s item id
					Reporter.log("Record First item’s item id");
					String itemId = getObject("LNK_FirstProductID_ACMEBRAND").getText();				// Store first item id into a string variable
					
					
					// Click to open First item from list
					Reporter.log("Click to open First item from list");
					WebUIAutomation.clickObj("LNK_FirstProductID_ACMEBRAND");
					
					
					// Check Add to Cart button is displayed on UI or not
					Assert.assertTrue(WebUIAutomation.isObjPresent("BTN_AddToCart_PRODUCTPAGE", 30), "Add To Cart button isn't displayed on the UI");
					
					// Click Add To Cart
					Reporter.log("Click Add To Cart");
					WebUIAutomation.clickObj("BTN_AddToCart_PRODUCTPAGE");
					
					
					// Click on Cart
					Reporter.log("Click on Cart");
					WebUIAutomation.clickObj("LNK_ViewCart_HEADER");
					
					
					// Wait for 5sec
					Thread.sleep(5000);
					
					
					// Verify that cart opens
					Reporter.log("Verify that cart opens");
					Assert.assertTrue(driver.getTitle()
							.equalsIgnoreCase("Shopping cart - TEST-MROSupply.com")
								, "User has not redirected to the 'Shopping Cart' page");				// Verify that cart opens or not
					
					
					// Verify that matching item ID added to cart
					Reporter.log("Verify that cart matching item ID added to cart");
					
					String cartItemId = getObject("LNK_FirstProductID_SHOPPINGCART").getText();			// Store cart item id into a String variable cartItemId
					
					Assert.assertTrue(itemId.equalsIgnoreCase(cartItemId)
							, "Item ID of the product which we have added in the cart"
									+ " and the Item ID of the product which is displayed"
										+ " in the cart are not matching");								// Verify that cart contains matching item ID or not 
					
					
					// Delete item from cart
					Reporter.log("Delete item from cart");
					WebUIAutomation.clickObj("BTN_RemoveFirstProductFromCart_SHOPPINGCART");
					
					
					// Wait for 5sec
					Thread.sleep(5000);
					
					
					// Verify cart containing 0 items
					Reporter.log("Verify cart containing 0 items");
					
					String emptyCartMessage = 
							getObject("TXT_EmptyCartMessage_SHOPPINGCART").getText();					// Store empty cart message into a String variable emptyCartMessage
					
					Assert.assertTrue(emptyCartMessage
							.equalsIgnoreCase("(Your Cart is Empty)")
								, "Cart Empty message is not displayed");								// Verify cart containing 0 items after deleting items from cart
					
					
					// Wait for 5sec
					Thread.sleep(5000);
					
				}catch(Exception e){
					Assert.assertTrue(false,"Caught Exception");
					e.printStackTrace();
				}
						
			}																							// end of test method testItemsInCartFromProductDetailsPage()
}
