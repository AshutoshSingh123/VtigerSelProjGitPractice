package com.vtiger.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

public class CreateOrganizationWithIndustryTest {
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


		String orgName=eLib.getDataFromExcel("org", 4, 2)+ jLib.getRandomNum();
		String industry=eLib.getDataFromExcel("org", 4, 3);
		String type=eLib.getDataFromExcel("org", 4, 4);
		

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

		Select industry_select=new Select(driver.findElement(By.name("industry")));
		industry_select.selectByVisibleText(industry);

		Select org_type_select=new Select(driver.findElement(By.name("accounttype")));
		org_type_select.selectByVisibleText(type);

		//click on save
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();


		//verify the expected result: industry and type

		try {
			String actIndustry= driver.findElement(By.id("dtlview_Industry")).getText();
			if(actIndustry.equals(industry)) {
				System.out.println(industry+" info===PASS");
			}else System.out.println(industry+" info===FAIL");

			String actType= driver.findElement(By.id("dtlview_Type")).getText();
			if(actType.equals(type)) {
				System.out.println(type+" info===PASS");
			}else System.out.println(type+" info===FAIL");
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
