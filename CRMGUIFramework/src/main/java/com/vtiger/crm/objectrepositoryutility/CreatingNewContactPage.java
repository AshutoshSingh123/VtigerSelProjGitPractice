package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage {
	
	WebDriver driver;
	WebDriverUtility wLib=new WebDriverUtility();
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement addOrgBtn;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="phone")
	private WebElement phoneNumEdt;
	
	@FindBy(name="support_start_date")
	private WebElement supStrtDt;
	
	@FindBy(name="support_end_date")
	private WebElement supEndDt;
		
	
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getPhoneNumEdt() {
		return phoneNumEdt;
	}
	
	public WebElement getAddOrgBtn() {
		return addOrgBtn;
	}
		
	public WebElement getSupStrtDt() {
		return supStrtDt;
	}

	public WebElement getSupEndDt() {
		return supEndDt;
	}

	public void createContact(String lastname) {
		lastNameEdt.sendKeys(lastname);
		saveBtn.click();
	}
		
	public void createContact(String lastname, String phoneNum) {
		lastNameEdt.sendKeys(lastname);
		phoneNumEdt.sendKeys(phoneNum);
		saveBtn.click();
	}
	
	public void createContact(String lastname, String startdate, String enddate) {
		//wLib.waitForPageLoad(driver);
		
		lastNameEdt.sendKeys(lastname);
		
		supStrtDt.clear();
		supEndDt.clear();
		
		supStrtDt.sendKeys(startdate);
		supEndDt.sendKeys(enddate);
		saveBtn.click();
	}
}
