package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductPage productPage;
	
	InputStream datais;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		
		/*
		String dataFileName="data/loginUsers.json";
		datais=getClass().getClassLoader().getResourceAsStream(dataFileName);
		JSONTokener tokener=new JSONTokener(datais);
		loginUsers=new JSONObject(tokener);
		*/
		
		//good practice to close all input streams
		
		try {
			
			String dataFileName="data/loginUsers.json";
			datais=getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener=new JSONTokener(datais);
			loginUsers=new JSONObject(tokener);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(datais!=null) {
				datais.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {

		loginPage = new LoginPage();

		System.out.println("\n" + "******Starting Test :" + m.getName() + "*******" + "\n");

	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void invalidUserName() {
		
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressLoginBtn();

		String actErrTxt = loginPage.getErrTxt();

		String expErrTxt = "Username and password do not match any user in this service.";

		System.out.println("Actual Error Text : " + actErrTxt + "\n" + "Expected Error Text : " + expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);
	
		
		/*
		 
		try {

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);

			e.printStackTrace(pw);
			System.out.println(sw.toString());

			Assert.fail(sw.toString());
		}
		
		*/
		
		
	}

	@Test
	public void invalidPassword() {

		loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		loginPage.pressLoginBtn();

		String actErrTxt = loginPage.getErrTxt();

	//	String expErrTxt = "Username and password do not match any user in this service.";
		String expErrTxt = strings.get("err_invalid_user_or_password");
		

		System.out.println("Actual Error Text : " + actErrTxt + "\n" + "Expected Error Text : " + expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);

	}

	@Test
	public void validCredentials() {

		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		productPage = loginPage.pressLoginBtn();

		String validationTxt = productPage.getTitle();

	//	String expTxt = "PRODUCTS";
		String expTxt = strings.get("txt_valid");
		

		System.out.println("Actual Title Text : " + validationTxt + "\n" + "Expected Title Text : " + expTxt);

		Assert.assertEquals(validationTxt, expTxt);

	}

}
