package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {

	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement userNameTxtFld;

	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement passwordTxtFld;

	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement loginBtn;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@cont"
			+ "ent-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errTxt;

	public LoginPage enterUserName(String username) {

		sendKeys(userNameTxtFld, username);
		return this;
	}

	public LoginPage enterPassword(String password) {

		sendKeys(passwordTxtFld, password);
		return this;

	}

	public ProductPage pressLoginBtn() {

		click(loginBtn);
		return new ProductPage();
	}

	public String getErrTxt() {

		return getAttribute(errTxt, "Text");

	}

}
