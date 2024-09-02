package com.vtiger.crm.orgtestwithpom_combinedtests_testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.vtiger.crm.listenerutility.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "get org name from excel");
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNum();
		
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		/* navigate to organization module*/
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "click on create new org button");
		
		/* click on create organization button */
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "enter orgname and click save to create the org");
		/* enter all details and create new organization */
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "verify the created org");
		/* verification header message expected result */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		/*		
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is verified===PASS");
		} else
			System.out.println(orgName + " is not verified===FAIL");
		 */


		/* verify using assert statements */

		Assert.assertTrue(actOrgName.contains(orgName));


		// verify the expected result: header and created organization name

		/*
		 * try { String created_orgHeader=
		 * driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 * String actOrgName=
		 * driver.findElement(By.id("dtlview_Organization Name")).getText();
		 * if(created_orgHeader.contains(orgName) && actOrgName.equals(orgName)) {
		 * System.out.println("organization "+orgName+" created===PASS"); String
		 * orgID=driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[2]")).getText
		 * (); System.out.println(orgID); eLib.setDataIntoExcel("org", 1, 5, orgID); }
		 * }catch(Exception e) { driver.switchTo().alert().accept(); System.out.
		 * println("organization name already exists====FAIL\nenter another organization name"
		 * ); } finally { //logout driver.findElement(By.xpath(
		 * "//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]")).click();
		 * driver.findElement(By.linkText("Sign Out")).click(); }
		 */
	}


	@Test(groups="regressionTest")
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
		String actType= oip.getTypeTxFld().getText();

		/*	if(actIndustry.equals(industry)) {
			System.out.println(industry+" info===PASS");
		}else System.out.println(industry+" info===FAIL");

		
		if(actType.equals(type)) {
			System.out.println(type+" info===PASS");
		}else System.out.println(type+" info===FAIL");	
		 */

		SoftAssert soft = new SoftAssert();

		soft.assertTrue(actIndustry.equals(industry));
		soft.assertTrue(actType.equals(type));

		soft.assertAll();


	}

	@Test(groups="regressionTest")
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
		String actOrgName= oip.getOrgName().getText();
		
	/*	if(actPhone.equals(phoneNum)) {
			System.out.println(phoneNum+" info===PASS");
		}else System.out.println(phoneNum+" info===FAIL");

		
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+" info===PASS");
		}else System.out.println(orgName+" info===FAIL");
	*/
		
		SoftAssert soft = new SoftAssert();

		soft.assertTrue(actPhone.equals(phoneNum));
		soft.assertTrue(actOrgName.contains(orgName));

		soft.assertAll();
		
	}



}
