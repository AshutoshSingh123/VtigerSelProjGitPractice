package com.vtiger.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

/**
 * utility class fro accessing static pool variables (test and driver)
 * @author Ashutosh
 */
public class UtilityClassObject {
	
	/**
	 * declaring the variables as threads
	 */
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	/**
	 * getter method for test variable
	 * @return
	 */
	public static ExtentTest getTest() {
		return test.get();
	}
	
	/**
	 * setter method for test variable
	 * @param actTest
	 */
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	
	/**
	 * getter method for driver variable from static pool
	 * @return driver instance
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	/**
	 * setter method for driver variable 
	 * @param actDriver
	 */
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
		
	}
	
}