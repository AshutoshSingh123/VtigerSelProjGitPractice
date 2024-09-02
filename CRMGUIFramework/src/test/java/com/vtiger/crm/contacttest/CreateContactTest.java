package com.vtiger.crm.contacttest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactTest {
	
	public static void main(String[] args) throws Throwable {
		
		/*Create Object of Utility classes to access the files */
		
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib= new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//read common data from property file using file utility class
		String BROWSER=fLib.getDataFromPropertyFile("browser");
		String URL=fLib.getDataFromPropertyFile("url");
		String USERNAME=fLib.getDataFromPropertyFile("username");
		String PASSWORD=fLib.getDataFromPropertyFile("password");
		
		
		//read testscript data from excel file and concatinate a random number using java utility
		String lastName=eLib.getDataFromExcel("contact", 1, 2)+ jLib.getRandomNum();
		
		
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

		driver.findElement(By.name("lastname")).sendKeys(lastName);
			
		//click on save
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		
		//verify the expected result: header text and lastname
		
		try {
			String actLastName= driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actLastName.equals(lastName)) {
				System.out.println(lastName+" info===PASS");
			}else System.out.println(lastName+" info===FAIL");
			
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
