package com.salesforce.zoho.pages.campaigns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class CampaignsListPage extends BasePage {
	
	
	String deleteIconXp = "//a[text()='name']/../..//a[text()='Del']";
	
	String campaignNameXp = "//a[text()='name']";
	
	@FindBy(xpath="//input[@value='New Campaign']")
	private WebElement newCampaignButton;
	
	@FindBy(xpath="//td[@class='tableData']/a")
	private List<WebElement> campaignsList;
	
	@FindBy(xpath="//td[contains(text(),'Campaign List')]")
	private WebElement campaignsListTitle;
	
	@FindBy(name="searchString")
	private WebElement searchTextField;
	
	@FindBy(name="Go")
	private WebElement goButton;
	
	@FindBy(name="cvid")
	private WebElement allCampaignsListBox;
	
	
	public WebElement getNewCampaignButton() {
		return newCampaignButton;
	}


	public List<WebElement> getCampaignsList() {
		return campaignsList;
	}


	public WebElement getCampaignsListTitle() {
		return campaignsListTitle;
	}


	public WebElement getSearchTextField() {
		return searchTextField;
	}


	public WebElement getGoButton() {
		return goButton;
	}


	public WebElement getAllCampaignsListBox() {
		return allCampaignsListBox;
	}


	public CampaignsListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public CreateCampaignPage clickOnNewCampaignButton() {
		webActionUtil.click(newCampaignButton);
		return new CreateCampaignPage(driver, webActionUtil);
	}
	
	public boolean isCampaignDisplayed(String campaignName) {
		for(WebElement campaign:campaignsList) {
			if(campaign.getText().equalsIgnoreCase(campaignName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyCampaignsListTitle() {
		return campaignsListTitle.isDisplayed();
	}
	
	public void searchCampaign(String campaginName) {
		webActionUtil.enterData(searchTextField, campaginName);
		webActionUtil.click(goButton);
	}
	
	public void viewCampaign(String visibleText) {
		webActionUtil.selectByVisbleText(allCampaignsListBox, visibleText);
	}


	public void deleteCampaign(String campainName) {
		deleteIconXp = deleteIconXp.replace("name", campainName);
		webActionUtil.click(driver.findElement(By.xpath(deleteIconXp)));
		webActionUtil.verifyAlertTextAndAccept("Are you sure ?");
		waitForInvisiblityOfCampaign(campainName);
	}
	
	public void waitForInvisiblityOfCampaign(String campainName) {
		campaignNameXp = campaignNameXp.replace("name", campainName);
		webActionUtil.waitForInvisibility(driver.findElement(By.xpath(campaignNameXp)));
	}
}
