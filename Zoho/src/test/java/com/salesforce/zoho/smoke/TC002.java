package com.salesforce.zoho.smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.salesforce.zoho.common.BaseTest;
import com.salesforce.zoho.generic.ExcelLibrary;
import com.salesforce.zoho.pages.campaigns.CampaignDetailsPage;
import com.salesforce.zoho.pages.campaigns.CampaignsListPage;
import com.salesforce.zoho.pages.campaigns.CreateCampaignPage;
import com.salesforce.zoho.pages.leads.CreateLeadPage;
import com.salesforce.zoho.pages.leads.LeadDetailsPage;
import com.salesforce.zoho.pages.leads.LeadsListPage;

public class TC002 extends BaseTest {
	CampaignsListPage campaginsListPage;
	LeadsListPage leadsListPage;
	String campaignName;
	String lastName;
	String menuLinkName;
	@Test
	public void testCampaginCreation() {
		
		menuLinkName = ExcelLibrary.getStringData("TC002", 1, 0);
		campaignName = ExcelLibrary.getStringData("TC002", 1, 1);
		String startDate = ExcelLibrary.getStringData("TC002", 1, 2);
		String expectedRevenue = ExcelLibrary.getStringData("TC002", 1, 3).split("\\.")[0];
		
		String campaignType = ExcelLibrary.getStringData("TC002", 1, 4);
		String campaignStatus = ExcelLibrary.getStringData("TC002", 1, 5);
		String menuLinkName2 = ExcelLibrary.getStringData("TC002", 1, 6);
		String companyName = ExcelLibrary.getStringData("TC002", 1, 7);
		lastName = ExcelLibrary.getStringData("TC002", 1, 8);
		
		
		campaginsListPage = (CampaignsListPage) homePage.clickOnMenuLink(menuLinkName);
		Assert.assertEquals(campaginsListPage.verifyCampaignsListTitle(), true);
		CreateCampaignPage createCampaignPage=campaginsListPage.clickOnNewCampaignButton();
		Assert.assertEquals(createCampaignPage.verifyTitle(), true);
		CampaignDetailsPage campaignDetailsPage = createCampaignPage.createCampaigin(campaignName,
											startDate,
											expectedRevenue,
											campaignType,
											campaignStatus);
		Assert.assertEquals(campaignDetailsPage.verifyTitle(), true);
		homePage.clickOnMenuLink(menuLinkName);
		Assert.assertEquals(campaginsListPage.isCampaignDisplayed(campaignName), true);
		
		leadsListPage = (LeadsListPage) homePage.clickOnMenuLink(menuLinkName2);
		Assert.assertEquals(leadsListPage.verifyLeadsListPageTitle(), true);
		CreateLeadPage createLeadPage = leadsListPage.clickOnNewLeadButton();
		Assert.assertEquals(createLeadPage.verifyTitle(), true);
		LeadDetailsPage leadDetailsPage = createLeadPage.
											createALead(companyName, lastName, campaignName);
		Assert.assertEquals(leadDetailsPage.verifyTitle(), true);
		homePage.clickOnMenuLink(menuLinkName2);
		Assert.assertEquals(leadsListPage.verifyLeadsListPageTitle(),true);
		Assert.assertEquals(leadsListPage.isLeadDisplayed(lastName), true);
	}
	
	@AfterMethod
	public void deleteCampaign() {
		leadsListPage.deleteLead(lastName);
		Assert.assertEquals(leadsListPage.isLeadDisplayed(lastName), false);
		homePage.clickOnMenuLink(menuLinkName);
		campaginsListPage.deleteCampaign(campaignName);
		Assert.assertEquals(campaginsListPage.isCampaignDisplayed(campaignName), false);
		
	}
}
