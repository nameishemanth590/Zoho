package com.salesforce.zoho.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.campaigns.CampaignsListPage;
import com.salesforce.zoho.pages.leads.LeadsListPage;

public class HomePage extends BasePage {
	
	@FindBy(partialLinkText="Logout")
	private WebElement logoutLink;
	
	@FindBy(xpath="//td[@class='menuOffBg topBorder' or @class='menuOnBg']/a")
	private List<WebElement> allMenuLinks;
	
	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public List<WebElement> getAllMenuLinks() {
		return allMenuLinks;
	}

	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public BasePage clickOnMenuLink(String menuName) {
		for(WebElement link:allMenuLinks) {
			if(link.getText().equalsIgnoreCase(menuName)) {
				webActionUtil.click(link);
				break;
			}
		}
		
		menuName=menuName.toLowerCase();
		
		switch(menuName) {
			case "campaigns": return new CampaignsListPage(driver, webActionUtil);
			case "leads": return new LeadsListPage(driver, webActionUtil);
			default: return null;
		}
	}
	
	public void logout() {
		webActionUtil.click(logoutLink);
	}
	
	
}
