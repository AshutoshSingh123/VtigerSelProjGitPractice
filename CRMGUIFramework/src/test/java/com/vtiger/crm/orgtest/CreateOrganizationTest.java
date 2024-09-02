package com.vtiger.crm.orgtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationTest {
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


		String orgName=eLib.getDataFromExcel("org", 1, 2)+ jLib.getRandomNum();


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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(By.xpath("//img[contains(@title,'Create Organization')]")).click();

		driver.findElement(By.name("accountname")).sendKeys(orgName);

		Select org_type_select=new Select(driver.findElement(By.name("accounttype")));

		org_type_select.selectByValue("Investor");

		driver.findElement(By.name("notify_owner")).click();

		driver.findElement(By.name("bill_street")).sendKeys("sample_street");

		driver.findElement(By.id("bill_city")).sendKeys("sample_city");

		driver.findElement(By.id("bill_country")).sendKeys("sample_country");

		driver.findElement(By.xpath("(//input[@name='cpy'])[2]")).click();

		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();


		//verify the expected result: header and created organization name

		try {
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


		}

		driver.quit();


	}
}
