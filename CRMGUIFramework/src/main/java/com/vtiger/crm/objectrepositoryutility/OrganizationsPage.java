package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	
	@FindBy(xpath ="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;

	@FindBy(name = "search_text")
	private WebElement searchEdt;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;


	public OrganizationsPage(WebDriver driver) throws Exception {
		this.driver=driver;
		Thread.sleep(5000);
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}
}
