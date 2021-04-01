package com.salesforce.zoho.pages.common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(id="userName")
	private WebElement loginNameTextField;
	
	@FindBy(id="passWord")
	private WebElement passwordTextField;
		
	@FindBy(name="j_remember")
	private WebElement rememberCheckBox;
	
	@FindBy(xpath="//input[@title='Sign In']")
	private WebElement signInButton;
	
	
	public WebElement getLoginNameTextField() {
		return loginNameTextField;
	}


	public WebElement getPasswordTextField() {
		return passwordTextField;
	}


	public WebElement getRememberCheckBox() {
		return rememberCheckBox;
	}


	public WebElement getSignInButton() {
		return signInButton;
	}


	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	//Action Methods
	public HomePage login(String user, String pwd) {
		webActionUtil.enterData(loginNameTextField,user);
		webActionUtil.enterData(passwordTextField,pwd);
		webActionUtil.click(signInButton);
		return new HomePage(driver, webActionUtil);
	}
}
