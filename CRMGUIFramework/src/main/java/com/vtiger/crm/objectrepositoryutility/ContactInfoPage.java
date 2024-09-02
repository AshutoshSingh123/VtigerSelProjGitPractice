package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	
	@FindBy(className ="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id ="dtlview_Last Name")
	private WebElement lastnameTxtFld;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement supportStrtDt;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement supportEndDt;
	
	WebDriver driver;
	
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getLastnameTxtFld() {
		return lastnameTxtFld;
	}

	public WebElement getSupportStrtDt() {
		return supportStrtDt;
	}

	public WebElement getSupportEndDt() {
		return supportEndDt;
	}
	
	
	
}
