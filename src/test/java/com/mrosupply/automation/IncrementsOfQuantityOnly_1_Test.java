package com.mrosupply.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class IncrementsOfQuantityOnly_1_Test extends TestBase {
	
	@BeforeTest
	public void doIHaveToSkip(){
		
		if(!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			throw new SkipException("Runmode set to No");
		}
	}
	
	/**
	 * This test method will check -
	 * 		1. The items quantity 0 in 'Shopping Cart' of 'MRO Supply'
	 * 		Web application and respective validation message
	 * 		( Minimum Order Quantity for Item #885586 is 1 )
	 */
		
	@Test
	public void testIncrementsOfQuantityOnly_1() {
		
		try{
			//Start of the Test
			  System.out.println("Increments of qty_1 only test has been started");
			   
			// Precondition: Remove all existing items from cart

			
			// Click on 'Brands' link it will navigate to "https://mrosupply.com/brands/" 
			Reporter.log("Click on 'Brands' link");
			if(!WebUIAutomation.clickObj("LNK_Brand_HOMEPAGE")) {
				Assert.fail("Brands link has not been clicked successfully");
			}
				
			
			
			// Click on 'Acme' link it will navigate to "https://mrosupply.com/brands/acme/"
			Reporter.log("Click 'Acme' link");
			if(!WebUIAutomation.clickObj("LNK_Acme_BRANDSPAGE")) {
				Assert.fail("Acme link has not been clicked successfully");
			}
			
			WebUIAutomation.isObjPresent("LNK_FirstProductID_ACMEBRAND", 30);
			
			// Record first item’s item id
			Reporter.log("Record first item’s item id");
			String iTemID = getObject("LNK_FirstProductID_ACMEBRAND").getText();					// Store the first item id into a String variable iTemID
			
			
			// Click on 'Add to cart' button 
			Reporter.log("Add to cart");
			if(!WebUIAutomation.clickObj("LNK_FirstItemAddToCart_ACMEBRAND")) {
			Assert.fail("Add to cart link has not been clicked successfully");
			}
					
			
			
			// Wait for 5sec
			Thread.sleep(5000);
			
			
			// Click on View Cart button
			Reporter.log("Click on Cart button");
			if(!WebUIAutomation.clickObj("LNK_ViewCart_HEADER")){
			Assert.fail("View to cart link has not been clicked successfully");
			}
			
			
			// Change item qty to 0 
			Reporter.log("Change item qty to 0");
			if(!WebUIAutomation.setText("BTN_Qty_SEARCH", "0")){            
				Assert.fail("Text has not been successfully added into qty_search field");
			}
			
			
			// Click on Checkout button
			Reporter.log("Click Checkout button");
			if(!WebUIAutomation.clickObj("BTN_CheckOut_SEARCH")) {
				Assert.fail("Checkout button has not been successfully clicked");
			}
			
			
			// Verifying attention message displays : 
			// Minimum Order Quantity for Item #885586 is 1  
			Reporter.log("Verifying Attention, message displays: Minimum Order Quantity"
					+ " for Item #"+iTemID+" is 1");
			WebUIAutomation.getObject("MSG_MinimumOrderQuantity_SEARCH").getText();
			boolean condMinQty0 = 
					WebUIAutomation.getObject("MSG_MinimumOrderQuantity_SEARCH").getText()
						.contains("Minimum Order Quantity for Item #"+iTemID+" is 1");					// Store the verification result into a boolean variable condMinQty0
			
			Assert.assertTrue(condMinQty0, "Minimum Order Quantity for"
					+ " Item #"+iTemID+" is 1 - not displayed correctly");								// Verify Attention message
				
						
			
            } catch(Exception e) {
            	Assert.assertTrue(false,"Caught Exception");
            	e.printStackTrace();
		}																							// try-catch block end																
		
	}	
}   


