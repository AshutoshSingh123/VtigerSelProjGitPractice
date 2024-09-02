package com.vtiger.crm.contacttestwithpom;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.ContactInfoPage;
import com.vtiger.crm.objectrepositoryutility.ContactsPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewContactPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrg extends BaseClass {
	@Test
	public void createContactWithOrg() throws Throwable {
		
		String orgName=eLib.getDataFromExcel("contact", 7, 2)+ jLib.getRandomNum();
		String contactLastName=eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNum();

		//navigate to contacts module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		//verify organization
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String created_orgHeader=	oip.getHeaderMsg().getText();
		if(created_orgHeader.contains(orgName)) {
			System.out.println(orgName+" created org info===PASS");
		}else System.out.println(orgName+" created org info===FAIL");		
		
		//navigate to contacts module
		hp.getContactLink().click();;

		//click on create contact link
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		//enter the details and create new contact
		CreatingNewContactPage cnc=new CreatingNewContactPage(driver);
		cnc.getLastNameEdt().sendKeys(contactLastName);

		cnc.getAddOrgBtn().click();
		//switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");


		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		//switch back to parent window
		wLib.switchToTabOnURL(driver, "module=Contacts");


		//click on save
		cnc.getSaveBtn().click();
		//verify headerInfo and orgname integration
		ContactInfoPage cip=new ContactInfoPage(driver);
		String created_cont=cip.getHeaderMsg().getText();
		if(created_cont.contains(contactLastName)) {
			System.out.println(contactLastName+" contact info===PASS");
		}else System.out.println(contactLastName+" contact info==FAIL");

		String createdWithOrg=	driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(createdWithOrg.trim().equals(orgName)) {
			System.out.println(orgName+" info===PASS with contact");
		}else System.out.println(orgName+" info===FAIL with contact");	
	}
}