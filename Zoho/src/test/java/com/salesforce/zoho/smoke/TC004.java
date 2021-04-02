package com.salesforce.zoho.smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.salesforce.zoho.common.BaseTest;
import com.salesforce.zoho.generic.ExcelLibrary;
import com.salesforce.zoho.pages.campaigns.CampaignDetailsPage;
import com.salesforce.zoho.pages.campaigns.CampaignsListPage;
import com.salesforce.zoho.pages.campaigns.CreateCampaignPage;

public class TC004  extends BaseTest  {
	CampaignsListPage campaginsListPage;
	String campaignName;
	@Test
	public void testCampaginCreation() {
		
		String menuLinkName = ExcelLibrary.getStringData("TC001", 1, 0);
		campaignName = ExcelLibrary.getStringData("TC001", 1, 1);
		String startDate = ExcelLibrary.getStringData("TC001", 1, 2);
		String expectedRevenue = ExcelLibrary.getStringData("TC001", 1, 3).split("\\.")[0];
		
		String campaignType = ExcelLibrary.getStringData("TC001", 1, 4);
		String campaignStatus = ExcelLibrary.getStringData("TC001", 1, 5);
		

		
		
		campaginsListPage = (CampaignsListPage) homePage.clickOnMenuLink(menuLinkName);
		Assert.assertEquals(campaginsListPage.verifyCampaignsListTitle(), true);
		CreateCampaignPage createCampaignPage=campaginsListPage.clickOnNewCampaignButton();
		Assert.assertEquals(createCampaignPage.verifyTitle(), false);
		CampaignDetailsPage campaignDetailsPage = createCampaignPage.createCampaigin(campaignName,
											startDate,
											expectedRevenue,
											campaignType,
											campaignStatus);
		Assert.assertEquals(campaignDetailsPage.verifyTitle(), true);
		homePage.clickOnMenuLink(menuLinkName);
		Assert.assertEquals(campaginsListPage.isCampaignDisplayed(campaignName), true);
	}
	
	@AfterMethod
	public void deleteCampaign() {
		campaginsListPage.deleteCampaign(campaignName);
		Assert.assertEquals(campaginsListPage.isCampaignDisplayed(campaignName), false);
	}
}
