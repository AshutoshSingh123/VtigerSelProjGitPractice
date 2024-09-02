package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	WebDriver driver;

	@FindBy(xpath ="//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;

	@FindBy(name = "search_text")
	private WebElement searchEdt;

	@FindBy(name = "search_field")
	private WebElement searchDD;

	@FindBy(name="submit")
	private WebElement searchBtn;


	public ContactsPage(WebDriver driver) throws Exception {
		this.driver=driver;
		Thread.sleep(3000);
		PageFactory.initElements(driver, this);
	}


	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
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
