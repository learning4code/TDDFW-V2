package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductPage productPage;
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }
  
  @BeforeMethod
  public void beforeMethod(Method m) {
	  
	  loginPage=new LoginPage();
	  
	  System.out.println("\n"+"******Starting Test :"+m.getName()+"*******"+"\n");
	  
  }

  @AfterMethod
  public void afterMethod() {
  }

  @Test
	public void invalidUserName() {

	  	loginPage.enterUserName("InvalidUsername");
	  	loginPage.enterPassword("secret_sauce");
	  	loginPage.pressLoginBtn();

	  	String actErrTxt=loginPage.getErrTxt();

		String expErrTxt = "Username and password do not match any user in this service.";
		
		System.out.println("Actual Error Text : "+actErrTxt+"\n"+"Expected Error Text : "+expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);

	}

	@Test
	public void invalidPassword() {


	  	loginPage.enterUserName("standard_user");
	  	loginPage.enterPassword("InvalidPassword");
	  	loginPage.pressLoginBtn();

	  	String actErrTxt=loginPage.getErrTxt();

		String expErrTxt = "Username and password do not match any user in this service.";
		
		System.out.println("Actual Error Text : "+actErrTxt+"\n"+"Expected Error Text : "+expErrTxt);

		Assert.assertEquals(actErrTxt, expErrTxt);

	}

	@Test
	public void validCredentials() {


	  	loginPage.enterUserName("standard_user");
	  	loginPage.enterPassword("secret_sauce");
	  	productPage=loginPage.pressLoginBtn();

	  	String validationTxt=productPage.getTitle();

		String expTxt = "PRODUCTS";
		
		System.out.println("Actual Title Text : "+validationTxt+"\n"+"Expected Title Text : "+expTxt);
		
		Assert.assertEquals(validationTxt, expTxt);

	}

}
