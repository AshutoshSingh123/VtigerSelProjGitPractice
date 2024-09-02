package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createProdImgBtn;
	
}
