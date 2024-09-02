package com.vtiger.crm.orgtestwithpom;

import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithIndustryTest extends BaseClass{
	
	@Test
	public void createOrgWithIndustry() throws Throwable {
		
		String orgName=eLib.getDataFromExcel("org", 4, 2)+ jLib.getRandomNum();
		String industry=eLib.getDataFromExcel("org", 4, 3);
		String type=eLib.getDataFromExcel("org", 4, 4);
		
		
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry,type);


		//verify the expected result: industry and type
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		
			String actIndustry= oip.getIndustryTxFld().getText();
			if(actIndustry.equals(industry)) {
				System.out.println(industry+" info===PASS");
			}else System.out.println(industry+" info===FAIL");

			String actType= oip.getTypeTxFld().getText();
			if(actType.equals(type)) {
				System.out.println(type+" info===PASS");
			}else System.out.println(type+" info===FAIL");

		}
	}
