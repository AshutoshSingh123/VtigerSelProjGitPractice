package com.vtiger.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrg {
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
		
				
		String orgName=eLib.getDataFromExcel("contact", 7, 2)+ jLib.getRandomNum();
		String contactLastName=eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNum();

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

		wLib.waitForPageLoad(driver);
		
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(By.xpath("//img[contains(@title,'Create Organization')]")).click();

		driver.findElement(By.name("accountname")).sendKeys(orgName);

		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		//verify organization
		System.out.println("Org Creation verification----------------------------------Start");
		String created_orgHeader=	driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actOrgName= driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(created_orgHeader.contains(orgName) && actOrgName.equals(orgName)) {
			System.out.println(orgName+" created org info===PASS");
		}else System.out.println(orgName+" created org info===FAIL");
		System.out.println("Org Creation verification----------------------------------End");
		
		//navigate to contacts module
		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.xpath("//img[contains(@title,'Create Contact')]")).click();

		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch back to parent window
		wLib.switchToTabOnURL(driver, "module=Contacts");
				

		//click on save
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		//verify headerInfo and orgname integration
		System.out.println("contact creation with Org verification----------------------------------Start");
		try {
			String created_cont=	driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(created_cont.contains(contactLastName)) {
				System.out.println(contactLastName+" contact info===PASS");
			}else System.out.println(contactLastName+" contact info==FAIL");
			
			String createdWithOrg=	driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(createdWithOrg.trim().equals(orgName)) {
				System.out.println(orgName+" info===PASS with contact");
			}else System.out.println(orgName+" info===FAIL with contact");
			
		}catch(Exception e) {			
			driver.switchTo().alert().accept();
		}
		System.out.println("contact creation with Org verification----------------------------------End");
		//logout
		driver.findElement(By.xpath("//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();

		
	}
}
