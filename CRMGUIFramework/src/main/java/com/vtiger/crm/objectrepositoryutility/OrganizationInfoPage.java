package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	@FindBy(className ="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryTxFld;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeTxFld;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneTxFld;
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getIndustryTxFld() {
		return industryTxFld;
	}

	public WebElement getTypeTxFld() {
		return typeTxFld;
	}

	public WebElement getPhoneTxFld() {
		return phoneTxFld;
	}	
	
}
