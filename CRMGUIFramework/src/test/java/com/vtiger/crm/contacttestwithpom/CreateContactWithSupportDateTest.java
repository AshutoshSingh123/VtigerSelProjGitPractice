package com.vtiger.crm.contacttestwithpom;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.ContactInfoPage;
import com.vtiger.crm.objectrepositoryutility.ContactsPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewContactPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;

public class CreateContactWithSupportDateTest  extends BaseClass{
	
	@Test
	public void createContactWithSupportDate() throws Throwable {

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

		
		//use utility methods to capture the dates
		String startDate=jLib.getSysDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		System.out.println(startDate);
		System.out.println(endDate);
		
		
//		cnc.getSupEndDt().sendKeys(startDate);
//		cnc.getSupEndDt().sendKeys(endDate);
//		cnc.getSaveBtn().click();
		
//		driver.findElement(By.name("support_start_date")).clear();
//		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
//		driver.findElement(By.name("support_end_date")).clear();
//		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		
		cnc.createContact(lastName, startDate, endDate);
		
		wLib.waitForPageLoad(driver);

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

}
