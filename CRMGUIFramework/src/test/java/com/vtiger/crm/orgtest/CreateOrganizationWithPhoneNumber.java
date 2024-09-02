package com.vtiger.crm.orgtest;

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

public class CreateOrganizationWithPhoneNumber {
	
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


		String orgName=eLib.getDataFromExcel("org", 7, 2)+ jLib.getRandomNum();
		String phoneNum=eLib.getDataFromExcel("org", 7, 3);


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

		driver.findElement(By.id("phone")).sendKeys(phoneNum);


		//click on save
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();


		//verify the expected result: phoneNUmber

		try {
			String actPhone= driver.findElement(By.id("dtlview_Phone")).getText();
			if(actPhone.equals(phoneNum)) {
				System.out.println(phoneNum+" info===PASS");
			}else System.out.println(phoneNum+" info===FAIL");

		}catch(Exception e) {			
			driver.switchTo().alert().accept();
			System.out.println("===test Failed===");

		}
		finally {
			//logout
			driver.findElement(By.xpath("//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]")).click();
			driver.findElement(By.linkText("Sign Out")).click();

		}

		driver.quit();
	}
}
