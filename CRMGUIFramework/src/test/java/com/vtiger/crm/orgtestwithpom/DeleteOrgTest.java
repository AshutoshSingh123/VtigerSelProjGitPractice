package com.vtiger.crm.orgtestwithpom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInfoPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {

		//create object of utility classes
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib= new WebDriverUtility();


		String BROWSER=fLib.getDataFromPropertyFile("browser");
		String URL=fLib.getDataFromPropertyFile("url");
		String USERNAME=fLib.getDataFromPropertyFile("username");
		String PASSWORD=fLib.getDataFromPropertyFile("password");


		String orgName=eLib.getDataFromExcel("org", 10, 2)+ jLib.getRandomNum();
		


		WebDriver driver=null;

		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();

		}else {
			driver=new ChromeDriver();
		}
		
		//login to app
		driver.get(URL);
		wLib.waitForPageLoad(driver);
	
		LoginPage lp=new LoginPage(driver);
		
		lp.loginToApp(USERNAME, PASSWORD);
		
		//navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//click on create organization button
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//enter all details and create new organization
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		//verification header message expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+" is verified===PASS");
		}else System.out.println(orgName+" is not verified===FAIL");
		
		//go back to org page
		hp.getOrgLink().click();
		
		//search for organization
		op.getSearchEdt().sendKeys(orgName);
		wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		
		//dynamic xpath
		
		wLib.waitForPageLoad(driver);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		//in dynamic webtable, select & delete the organization
		driver.switchTo().alert().accept();
		
		//signout
//		Thread.sleep(1500);
		hp.logout();

		//verify the expected result: header and created organization name

	/*	try {
			String created_orgHeader=	driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			String actOrgName= driver.findElement(By.id("dtlview_Organization Name")).getText();
			if(created_orgHeader.contains(orgName) && actOrgName.equals(orgName)) {
				System.out.println("organization "+orgName+" created===PASS");
				String orgID=driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[2]")).getText();
				System.out.println(orgID);
				eLib.setDataIntoExcel("org", 1, 5, orgID);
			}
		}catch(Exception e) {			
			driver.switchTo().alert().accept();
			System.out.println("organization name already exists====FAIL\nenter another organization name");

		}
		finally {
			//logout
			driver.findElement(By.xpath("//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]")).click();
			driver.findElement(By.linkText("Sign Out")).click();


		}*/

		driver.quit();


	}
}
