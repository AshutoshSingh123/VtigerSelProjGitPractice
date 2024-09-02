package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	@FindBy(name="accountname")
	WebElement orgNameEdt;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(id="phone")
	private WebElement phoneTbx;
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String phone) {
		orgNameEdt.sendKeys(orgName);
		phoneTbx.sendKeys(phone);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industry,String type) {
		orgNameEdt.sendKeys(orgName);
		
		Select sel=new Select(industryDD);
		sel.selectByValue(industry);
		
		Select sel1=new Select(typeDD);
		sel1.selectByValue(type);
		
		saveBtn.click();
	}
}
