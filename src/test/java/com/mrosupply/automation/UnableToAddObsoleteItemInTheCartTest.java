package com.mrosupply.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UnableToAddObsoleteItemInTheCartTest extends TestBase {
	
	@BeforeTest	
	public void doIHaveToSkip(){
		
		if(!(WebUIAutomation.checkTestCaseRunmode(this.getClass().getSimpleName()))) {
			
			throw new SkipException("Runmode set to No");
		}
	}
	@Test
	public void testUnableToAddObsoleteItemInTheCart() {
		
		try{																					// try-catch block starts
			
			// Navigate to MRO Supply Website
			Reporter.log("Navigate to MRO Supply Website");
			driver.navigate().to(config.getProperty("testSiteURL"));
			
			
			// Search after an Obsolete item (our case 672155 )
			Reporter.log("Search after an Obsolete item (our case 672155 )");
			if(!WebUIAutomation.setText("TXT_SearchBox_HEADER", "672155")){
				
			Assert.fail("Text has not been entered successfully into the search text box");	
			}
			if(!WebUIAutomation.clickObj("IMG_SearchIcon_HEADER")){
				Assert.fail("Search Icon on header has not been successfully clicked");
			}
			
			
			// Try to add item to cart and Verify that is not possible
			Reporter.log("Try to add item to cart and Verify that is not "
					+ "possible");
			
			if(WebUIAutomation.isElementPresent("LNK_FirstItemAddToCart_ACMEBRAND")){
				Assert.fail("Add to cart button is present, Add to cart button should not present for obsolete item");
			}
			
			
			
			
			// Finish test
			Reporter.log("Finishing the 8th test Unable To Add Obsolete Item In"
					+ " The Cart");
			
			
		}catch(Exception e){
			Assert.assertTrue(false,"Caught Exception:"+ e.getMessage());
			e.printStackTrace();
		}																							// end of try-catch block 															
		
	}		

}
