package com.mrosupply.automation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * 
 * RegistrationProcess class is created for testing the Registration Process of  
 * 'MRO Supply' Web application and respective 
 * validation messages.
 * 
 */

public class RegistrationProcessTest extends TestBase {

	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {
			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData")
	public void testRegistrationProcess(String Password, String Password1,
			String FirstName, String LastName, String Phone, String Address1,
			String City, String PostalCode) {

	
		try {
			capabilities.setCapability("name", "RegistrationProcessTest");
			// Code to Make the Email ID Unique by adding date and time
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // create
																			// an
																			// instance
																			// of
																			// DateFormat
																			// and
																			// store
																			// into
																			// dateFormat
																			// variable
																			// dateFormat

			Calendar cal = Calendar.getInstance(); // create an instance of
													// calendar and store into a
													// Calendar variable cal

			String date = dateFormat.format(cal.getTime()); // Storing date
															// value to a String
															// variable date

			// Start of the Test
			System.out.println("Registration Process Test has been started");

			// Click Register
			Reporter.log("Click 'Register' ");
			if (!WebUIAutomation.clickObj("LNK_Register_MROSupply")) {

				Assert.fail("Register link has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// User info : Fill in info for Registration step 1
			Reporter.log("User info : Filling  info for Registration step 1");

			// Enter Email (Login)
			if (!WebUIAutomation.setText("TXT_Email_Register", "test" + date
					+ "161990@gmail.com")) {
				Assert.fail("Text has not been entered into Email text field");
			}

			// Enter Password
			if (!WebUIAutomation.setText("TXT_Password_Register", Password)) {
				Assert.fail("Text has not been entered into Password text field");
			}

			// Repeat Password
			if (!WebUIAutomation.setText("TXT_Password2_Register", Password1)) {
				Assert.fail("Text has not been entered into Password2 text field");
			}

			// Enter Firstname
			if (!WebUIAutomation.setText("TXT_FirstName_Register", FirstName)) {
				Assert.fail("Text has not been entered into FirstName text field");
			}

			// Enter Lastname
			if (!WebUIAutomation.setText("TXT_LastName_Register", LastName)) {
				Assert.fail("Text has not been entered into LastName text field");
			}

			// Enter Phone
			if (!WebUIAutomation.setText("TXT_Phone_Register", Phone)) {
				Assert.fail("Text has not been entered into Phone text field");
			}
			
			Reporter.log("User info : Successfully Filled  info for Registration step 1");

			// Click Next
			Reporter.log("Click Next");
			if (!WebUIAutomation.clickObj("BTN_Next_Register")) {
				Assert.fail("Next button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Shipping Info: Fill in info for Registration step 2
			Reporter.log("Shipping Info:  Fill in info for Registration step 2");

			// Enter FirstName
			if (!WebUIAutomation.setText("TXT_FirstName_Register2", FirstName)) {
				Assert.fail("2nd step - Text has not been entered into FirstName text field");
			}

			// Enter LastName
			if (!WebUIAutomation.setText("TXT_LastName_Register2", LastName)) {
				Assert.fail("2nd step - Text has not been entered into LastName text field");
			}

			// Enter Email
			if (!WebUIAutomation.setText("TXT_Email_Register2",
					"test161990@gmail.com")) {
				Assert.fail("2nd step - Text has not been entered into Email text field");

			}

			// Enter Phone
			if (!WebUIAutomation.setText("TXT_Phone_Register2", Phone)) {
				Assert.fail("2nd step - Text has not been entered into Phone text field");
			}

			// Enter Address1
			if (!WebUIAutomation.setText("TXT_Addresss1_Register2", Address1)) {
				Assert.fail("2nd step - Text has not been entered into Addresss1 text field");
			}

			// Enter City
			if (!WebUIAutomation.setText("TXT_City_Register2", City)) {
				Assert.fail("2nd step - Text has not been entered into City text field");
			}

			// Select the State
			// WebUIAutomation.selectValueFromDrpDwn("DPDN_State_Register2","AK");
			// // Default is AK so no need to select

			// Enter PostalCode
			if (!WebUIAutomation
					.setText("TXT_PostalCode_Register2", PostalCode)) {
				Assert.fail("2nd step - Text has not been entered into PostalCode text field");
			}
			// Shipping Info: Successfully Filled info for Registration step 2
			Reporter.log("Shipping Info:  Successfully Filled info for Registration step 2");
			Reporter.log("Shipping Info:  Fill in info for Registration step 2");

			// Click Next
			Reporter.log("Click Next");
			if (!WebUIAutomation.clickObj("BTN_Next_Register2")) {
				Assert.fail("2nd step - Next button has not been clicked successfully and "
						+ "subsequent page hasn't been displayed.");
			}

			// Click Register button
			Reporter.log("Click Register button");
			if (!WebUIAutomation.clickObj("BTN_Register_Register3")) {
				Assert.fail("Register button has not been clicked successfully"
						+ "and subsuqient page is not displayed");
			}

			// Verify text present: 'Registration has been completed
			// successfully.'
			Reporter.log("Verify text present: 'Registration has been completed successfully");

			boolean cond = WebUIAutomation
					.isElementPresent("MSG_ValidMsg_Register3"); // Store the
																	// status
																	// into a
																	// boolean
																	// variable
																	// cond fort
																	// successful
																	// registration
																	// message
																	// is
																	// displayed
																	// or not

			Assert.assertTrue(cond, "Confirmation message is not displayed"); // Verify
																				// Confirmation
																				// message
																				// for
																				// successful
																				// registration
																				// displayed
																				// as
																				// expected
																				// or
																				// not

			// driver.quit();

			// Signing Out
			Reporter.log("SIgning out");

			// Click on 'My Account'
			if (!WebUIAutomation.clickObj("LNK_MyAccount_LOGGEDINUSER")) {
				Assert.fail("MyAccount link has not been clicked successfully"
						+ "and subsuqient page is not displayed");
			}

			// Click on Signout
			if (!WebUIAutomation.clickObj("LNK_SignOut_LOGGEDINUSER")) {
				Assert.fail("Sign-out Link has not been clicked successfully"
						+ "and subsuqient page is not displayed");
			}

		} catch (Exception e) {

			Assert.assertTrue(false, "Caught Exception");
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getData() {

		return WebUIAutomation.getData("RegistrationProcessTest");
	}

	/*
	 * @AfterTest public void closeBrowser(){
	 * 
	 * driver.quit(); }
	 */

}
