package com.salesforce.zoho.pages.leads;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class LeadsListPage extends BasePage {
	
	String deleteXp="//a[text()='name']/../..//a[text()='Del']";
	String leadNameXp = "//a[text()='name']";
	
	@FindBy(xpath="//input[@value='New Lead']")
	private WebElement newLeadButton;
	
	@FindBy(xpath="//td[contains(text(),'Lead List')]")
	private WebElement pageTitle;
	
	
	@FindBy(xpath="//td[@class='tableData']/a[contains(@href,'Leads')]")
	private List<WebElement> leadsList;
	
	public String getDeleteXp() {
		return deleteXp;
	}

	public WebElement getNewLeadButton() {
		return newLeadButton;
	}

	public WebElement getPageTitle() {
		return pageTitle;
	}

	public List<WebElement> getLeadsList() {
		return leadsList;
	}

	public LeadsListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public CreateLeadPage clickOnNewLeadButton() {
		webActionUtil.click(newLeadButton);
		return new CreateLeadPage(driver, webActionUtil);
	}
	
	public boolean verifyLeadsListPageTitle() {
		return pageTitle.isDisplayed();
	}
	
	public boolean isLeadDisplayed(String leadName) {
		for(WebElement lead:leadsList) {
			if(lead.getText().equalsIgnoreCase(leadName)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteLead(String leadName) {
		deleteXp = deleteXp.replace("name", leadName);
		webActionUtil.click(driver.findElement(By.xpath(deleteXp)));
		webActionUtil.verifyAlertTextAndAccept("Are you sure ?");
		waitForInvisiblityOfLead(leadName);
	}
	
	public void waitForInvisiblityOfLead(String leadName) {
		leadNameXp = leadNameXp.replace("name", leadName);
		webActionUtil.waitForInvisibility(driver.findElement(By.xpath(leadNameXp)));
	}
}
