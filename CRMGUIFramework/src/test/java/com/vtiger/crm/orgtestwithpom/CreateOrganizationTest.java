package com.vtiger.crm.orgtestwithpom;

import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void createOrgTest() throws Throwable {

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNum();

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verification header message expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is verified===PASS");
		} else
			System.out.println(orgName + " is not verified===FAIL");

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
}
