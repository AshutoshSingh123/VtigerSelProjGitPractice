package com.vtiger.crm.orgtestwithpom;

import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumber extends BaseClass {

	@Test
	public void createOrgWithPhNo() throws Throwable {

		String orgName=eLib.getDataFromExcel("org", 7, 2)+ jLib.getRandomNum();
		String phoneNum=eLib.getDataFromExcel("org", 7, 3);


		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, phoneNum);


		//verify the expected result: phoneNUmber
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);

		String actPhone= oip.getPhoneTxFld().getText();
		if(actPhone.equals(phoneNum)) {
			System.out.println(phoneNum+" info===PASS");
		}else System.out.println(phoneNum+" info===FAIL");

		String actOrgName= oip.getOrgName().getText();
		if(actOrgName.equals(orgName)) {
			System.out.println(orgName+" info===PASS");
		}else System.out.println(orgName+" info===FAIL");
	}
}
