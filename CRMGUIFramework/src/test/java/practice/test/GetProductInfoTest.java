package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String prodName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//capture the product info
		String x="//span[text()='"+prodName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	
//	@DataProvider
//	public Object[][] getData(){
//		Object[][] objAr=new Object[3][2];
//		objAr[0][0]="iphone";
//		objAr[0][1]="Apple iPhone 15 (128 GB) - Black";
//		objAr[1][0]="iphone";
//		objAr[1][1]="Apple iPhone 13 (128GB) - Midnight";
//		objAr[2][0]="iphone";
//		objAr[2][1]="Apple iPhone 13 (128GB) - Starlight";
//		
//		return objAr;
//	}
	
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		
		ExcelUtility eLib=new ExcelUtility();
		int rowCount=eLib.getRowCount("product");
		Object[][] objAr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++) {
			objAr[i][0]=eLib.getDataFromExcel("product", i+1, 0);
			objAr[i][1]=eLib.getDataFromExcel("product", i+1, 1);
		}
		
		return objAr;
	}
	
}
