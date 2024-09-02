package com.vtiger.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.crm.generic.databaseutility.DatabaseUtility;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

/**
 * base class for basic generic functions like opening/closing the browser
 * logging in and logging out
 * connecting/Disconnecting to/from DB
 * @author Ashutosh
 * 
 */
public class BaseClass {

	/*create object of utility classes*/
	public FileUtility fLib=new FileUtility();
	public JavaUtility jLib=new JavaUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public WebDriverUtility wLib= new WebDriverUtility();
	public DatabaseUtility dbLib=new DatabaseUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	/**
	 * gets executed before each suite execution
	 * connects to DB
	 * has @BeforeSuite annotation and group keys
	 * @throws SQLException
	 */
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("---connect to DB, Report config---");
		dbLib.getDBConnection();

	}
	
	/**
	 * gets executed after each suite execution
	 * disconnects to DB
	 * has @AfterSuite annotation and group keys
	 * @throws Throwable
	 */
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("---close DB, Report Backup---");
		dbLib.closeDBConnection();
	}
	
	/**
	 * gets executed before each class execution
	 * opens the browser
	 * can take parameter from xml file for browser using @Paramters annotation 
	 * @throws Throwable
	 */
	//@Parameters("BROWSER")
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC(/*String browser*/) throws Throwable {
		System.out.println("launch the browser");
		String BROWSER=	//browser;
				fLib.getDataFromPropertyFile("browser");

		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();

		}else {
			driver=new ChromeDriver();
		}
		sdriver=driver;
		
		/*share the static pool driver for multi use...with the help of thread local*/
		UtilityClassObject.setDriver(driver);
		
	}
	
	
	/**
	 * gets executed before each test method
	 * used for logging in to application
	 * @throws Throwable
	 */
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("login");
		LoginPage lp=new LoginPage(driver);
		String URL=	fLib.getDataFromPropertyFile("url");
		String USERNAME=	fLib.getDataFromPropertyFile("username");
		String PASSWORD=	fLib.getDataFromPropertyFile("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	/**
	 * gets executed after each test method
	 * used for logging out of the application
	 * @throws Throwable
	 */
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM() throws Throwable {
		System.out.println("logout");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	/**
	 * gets executed after each class execution
	 * used for closing the browser
	 */
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("close browser");
		driver.quit();
	}
}