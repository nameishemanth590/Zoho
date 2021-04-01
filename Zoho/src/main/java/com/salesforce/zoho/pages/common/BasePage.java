package com.salesforce.zoho.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.salesforce.zoho.generic.WebActionUtil;

public class BasePage {
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public BasePage(WebDriver driver, WebActionUtil webActionUtil) {
		this.driver=driver;
		this.webActionUtil=webActionUtil;
		PageFactory.initElements(driver, this);
	}
}
