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

/**
 * @author Ashutosh
 */
public class CreateContactTest extends BaseClass{

	@Test
	public void createContactTest() throws Throwable {

		/*read testscript data from excel file and concatinate a random number using java utility*/
		String lastName=eLib.getDataFromExcel("contact", 1, 2)+ jLib.getRandomNum();

		/*navigate to contacts module*/
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();;

		/*click on create contact link*/
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/*enter the details and create new contact*/
		CreatingNewContactPage cnc=new CreatingNewContactPage(driver);
		cnc.createContact(lastName);

		/*verify the expected result: header text and lastname*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actLastName= cip.getHeaderMsg().getText();
		if(actLastName.equals(lastName)) {
			System.out.println(lastName+" info===PASS");
		}else System.out.println(lastName+" info===FAIL");	
	}

	@Test
	public void createContactWithSupportDateTest() throws Throwable {

		//read testscript data from excel file and concatinate a random number using java utility
		String lastName=eLib.getDataFromExcel("contact", 4, 2)+ jLib.getRandomNum();

		//navigate to contacts module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();;

		//click on create contact link
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		//enter the details and create new contact
		CreatingNewContactPage cnc=new CreatingNewContactPage(driver);
		cnc.createContact(lastName);

		cnc.getSupStrtDt().clear();
		cnc.getSupEndDt().clear();
		
		//use utility methods to capture the dates
		String startDate=jLib.getSysDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);

		System.out.println(startDate);
		System.out.println(endDate);

		cnc.getSupStrtDt().sendKeys(startDate.trim());
		cnc.getSupEndDt().sendKeys(endDate.trim());
		cnc.getSaveBtn().click();

		//cnc.createContact(lastName, startDate, endDate);

		//verify the expected result: support start date and support end date
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actStartDte= cip.getSupportStrtDt().getText();
		if(actStartDte.equals(startDate)) {
			System.out.println(startDate+" info===PASS");
		}else System.out.println(startDate+" info===FAIL");

		String actEndDte= cip.getSupportEndDt().getText();
		if(actEndDte.equals(endDate)) {
			System.out.println(endDate+" info===PASS");
		}else System.out.println(endDate+" info===FAIL");

	}


@Test
public void createContactWithOrgTest() throws Throwable {

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
