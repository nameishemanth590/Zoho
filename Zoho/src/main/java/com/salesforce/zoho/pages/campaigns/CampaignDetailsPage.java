package com.salesforce.zoho.pages.campaigns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class CampaignDetailsPage extends BasePage {
	
	@FindBy(xpath="//td[@class='title hline']")
	private WebElement title;
	
	public CampaignDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public boolean verifyTitle() {
		return title.getText().contains("Campaign Details");
	}
}
