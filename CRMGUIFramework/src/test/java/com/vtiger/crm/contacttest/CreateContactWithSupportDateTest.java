package com.vtiger.crm.contacttest;

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

public class CreateContactWithSupportDateTest {

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

		//read testscript data from excel file and concatinate a random number using java utility
		String lastName=eLib.getDataFromExcel("contact", 4, 2)+ jLib.getRandomNum();


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

		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.xpath("//img[contains(@title,'Create Contact')]")).click();

		//use utility methods to capture the dates
		String startDate=jLib.getSysDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		System.out.println(startDate);
		System.out.println(endDate);

		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		//click on save
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();


		//verify the expected result: support start date and support end date

		try {
			String actStartDte= driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if(actStartDte.equals(startDate)) {
				System.out.println(startDate+" info===PASS");
			}else System.out.println(startDate+" info===FAIL");

			String actEndDte= driver.findElement(By.id("dtlview_Support End Date")).getText();
			if(actEndDte.equals(endDate)) {
				System.out.println(endDate+" info===PASS");
			}else System.out.println(endDate+" info===FAIL");


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
