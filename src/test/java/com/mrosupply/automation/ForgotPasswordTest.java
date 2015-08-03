package com.mrosupply.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/*
 * 
 * Forgot password class is created for testing the Forgot password functionality of 
 * the 'MRO Supply' Web application and respective validation messages.
 * 
 */

public class ForgotPasswordTest extends TestBase {

	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}

	@Test
	public void testForgotPassword() {

		try {
			capabilities.setCapability("name", "ForgotPasswordTest");
			Thread.sleep(3000);
			// Click on Sign In Button
			Reporter.log("Click on Sign-in button");
			if(!WebUIAutomation.clickObj("LNK_SignIn_HEADER")) {
				Assert.fail("Sign-in button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Wait for 5 sec
			Thread.sleep(5000);

			// Start of the Test
			System.out.println("Forgot password test has been started");

			// Click on "Click Here" link
			Reporter.log("Click on 'Click  Here'");
			if(!WebUIAutomation.clickObj("LNK_Clickhere_LOGINPAGE")) {
				Assert.fail("Click here link has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}
			
			Thread.sleep(5000);

			// Enter email Id to recover Password
			Reporter.log("Enter Mail Id");
			if(!WebUIAutomation.setText("TXT_PaswordresetEmail_PASSWORDRESET",
					"tdentald@gmail.com")) {
				Assert.fail("Text has not been entered into text field");
			}

			// Click on Reset Button
			Reporter.log("click on Reset button");
			if(!WebUIAutomation.clickObj("BTN_reset_PASSWORDRESET")) {
				Assert.fail("Reset button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Verify the message content �Email with further instructions about
			// password
			// reset has been sent� is displayed .
			Reporter.log("Verify that �Email with further instructions about password reset "
					+ "has been sent' is displayed");
			 WebElement msgText = (new WebDriverWait(driver, 30))
			 .until(ExpectedConditions.visibilityOf(WebUIAutomation.getObject("MSG_EmailSent_PASSWORDRESET")));
			 System.out.println(msgText);

			String instPassordRetest = getObject("MSG_EmailSent_PASSWORDRESET").getText(); // Storing message for comparison into String
			// variable instPassordRetest

			Assert.assertTrue(
					instPassordRetest
							.equalsIgnoreCase("Email with further instructions about password reset has been sent"),
					"The message -'Email with further instructions about password reset"
							+ " has been sent' has not been displayed correctly"); // Verify confirmation message for reset password is proper or not
		} catch (Exception e) {

			Assert.assertTrue(false,"Caught Exception");
			e.printStackTrace();
		} 
	}
	
	/*
	 * @AfterTest public void closeBrowser(){
	 * 
	 * // driver.quit(); }
	 */
}
