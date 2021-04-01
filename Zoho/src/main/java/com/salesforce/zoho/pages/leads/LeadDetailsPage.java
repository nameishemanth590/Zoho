package com.salesforce.zoho.pages.leads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class LeadDetailsPage extends BasePage {
	
	@FindBy(xpath="//td[@class='title hline']")
	private WebElement title;
	
	public LeadDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public boolean verifyTitle() {
		return title.getText().contains("Lead Details");
	}
}
