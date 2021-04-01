package com.salesforce.zoho.pages.campaigns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class CreateCampaignPage extends BasePage {
	
	@FindBy(xpath="//td[@class='title hline']")
	private WebElement title;
	
	@FindBy(name="property(Campaign Name)")
	private WebElement nameTextField;
	
	@FindBy(name="property(Start Date)")
	private WebElement startDateTextField;
	
	@FindBy(name="property(Expected Revenue)")
	private WebElement expectedRevenueTextField;
	
	@FindBy(name="property(Type)")
	private WebElement typeListBox;
	
	@FindBy(name="property(Status)")
	private WebElement statusListBox;
		
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Save & New']")
	private WebElement saveAndNewButton;
	
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Cancel']")
	private WebElement CancelButton;
	
	public WebElement getTitle() {
		return title;
	}

	public WebElement getNameTextField() {
		return nameTextField;
	}

	public WebElement getStartDateTextField() {
		return startDateTextField;
	}

	public WebElement getExpectedRevenueTextField() {
		return expectedRevenueTextField;
	}

	public WebElement getTypeListBox() {
		return typeListBox;
	}

	public WebElement getStatusListBox() {
		return statusListBox;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getSaveAndNewButton() {
		return saveAndNewButton;
	}

	public WebElement getCancelButton() {
		return CancelButton;
	}

	public CreateCampaignPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public boolean verifyTitle() {
		return title.getText().contains("Create Campaign");
	}
	
	public CampaignDetailsPage createCampaigin(String campaignName,
								String startDate,
								String expectedRevenue,
								String campaignType,
								String campaignStatus) {
		webActionUtil.enterData(nameTextField, campaignName);
		webActionUtil.enterData(startDateTextField, startDate);
		webActionUtil.enterData(expectedRevenueTextField, expectedRevenue);
		webActionUtil.selectByVisbleText(typeListBox, campaignType);
		webActionUtil.selectByVisbleText(statusListBox, campaignStatus);
		webActionUtil.click(saveButton);
		return new CampaignDetailsPage(driver, webActionUtil);
	}
}
