package com.vtiger.crm.objectrepositoryutility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	WebDriverUtility wLib=new WebDriverUtility();
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getCampaignLink() {
		return campaignLink;
	}
	
	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public void navigateToCampaignPage() {
		Actions act=new Actions(driver);
		act.moveToElement(moreLink).perform();
		
		campaignLink.click();
	}

	public void logout() throws Throwable {
		wLib.waitForPageLoad(driver);
		Actions act=new Actions(driver);
		Thread.sleep(5000);
		act.moveToElement(adminImg).perform();
		Thread.sleep(5000);
		signOutLink.click();
	}
}
