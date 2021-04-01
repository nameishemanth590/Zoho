package com.salesforce.zoho.pages.leads;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.zoho.generic.WebActionUtil;
import com.salesforce.zoho.pages.common.BasePage;

public class CreateLeadPage extends BasePage {
	
	@FindBy(name="property(Company)")
	private WebElement companyNameTextField;
	
	@FindBy(name="property(Last Name)")
	private WebElement lastNameTextField;
	
	@FindBy(xpath="//img[@title='Campaign Name Lookup']")
	private WebElement campaignNameLookupImg;
	
	@FindBy(xpath="//td[@class='tableData']/a")
	private List<WebElement> campaignListInLookUpWindow;
	
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Save & New']")
	private WebElement saveAndNewButton;
	
	@FindBy(xpath="//table[@id='secContent2']/following-sibling::table//input[@value='Cancel']")
	private WebElement CancelButton;
	
	@FindBy(xpath="//td[@class='title hline']")
	private WebElement title;
	
	public WebElement getCompanyNameTextField() {
		return companyNameTextField;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getCampaignNameLookupImg() {
		return campaignNameLookupImg;
	}

	public List<WebElement> getCampaignListInLookUpWindow() {
		return campaignListInLookUpWindow;
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

	public CreateLeadPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public void clickOnCampaignNameInLookUp(String campaignName) {
		for(WebElement campaign:campaignListInLookUpWindow) {
			if(campaign.getText().equalsIgnoreCase(campaignName)) {
				webActionUtil.click(campaign);
				break;
			}
		}
	}
	
	public LeadDetailsPage createALead(String companyName,
							String lastName,
							String campaignName) {
		webActionUtil.enterData(companyNameTextField, companyName);
		webActionUtil.enterData(lastNameTextField, lastName);
		webActionUtil.click(campaignNameLookupImg);
		webActionUtil.switchToWindow("Lookup");
		clickOnCampaignNameInLookUp(campaignName);
		webActionUtil.switchToParentWindow();
		webActionUtil.click(saveButton);
		return new LeadDetailsPage(driver, webActionUtil);
	}
	
	public boolean verifyTitle() {
		return title.getText().contains("Create Lead");
	}
	
}
