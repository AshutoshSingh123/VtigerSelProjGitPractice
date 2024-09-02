package com.vtiger.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;

/**
 * listener implementation(keeps listening/monitoring the execution for events like
 * suite start/finish
 * test start/pass/fail/skip
 * @author Ashutosh
 * 
 *
 */
public class ListenerImplementationClass extends BaseClass implements ITestListener,ISuiteListener{

	
	public ExtentSparkReporter spark;
	public static ExtentTest test;
	public static ExtentReports report;	
	
	/**
	 * 
	 * @return void
	 * @author Ashutosh
	 * 
	 */
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		ISuiteListener.super.onStart(suite);
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		/*spark report config*/
		spark=new ExtentSparkReporter("./AdvancedReport/report_"+timestamp+".html");
		spark.config().setDocumentTitle("CRM Test Suite Report");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		/*add environment info and create test*/
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "chrome-100");
	}
	
	/**
	 * takes backup of the extent report using flush() method
	 *
	 */
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		ISuiteListener.super.onFinish(suite);
		
		//report backup
		report.flush();
	}
	
	/**
	 * creates a test report using createTest() method and gives its reference to test variable to setter method
	 */
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		System.out.println("====>"+result.getMethod().getMethodName()+"===START===");
		ITestListener.super.onTestStart(result);
		
		test=report.createTest(result.getMethod().getMethodName());
		
		//share the static pool 'test' variable
		UtilityClassObject.setTest(test);
		
		test.log(Status.INFO,result.getMethod().getMethodName()+"===STARTED===");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("====>"+result.getMethod().getMethodName()+"===END===");
		test.log(Status.PASS,result.getMethod().getMethodName()+"===COMPLETED===");
		
		ITestListener.super.onTestSuccess(result);
		
		
	}
	
	/**
	 * gets executes at the time of test case failure
	 * takes a screenshot and attaches it to ExtentReport
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testname=result.getMethod().getMethodName();
		TakesScreenshot t=(TakesScreenshot)sdriver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		
		String sspath = t.getScreenshotAs(OutputType.BASE64);
		
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		try {
			FileUtils.copyFile(srcFile, new File("./screenshot/"+testname+"+"+timestamp+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromBase64String(sspath, testname+"_"+timestamp);
		
		test.log(Status.FAIL,result.getMethod().getMethodName()+"===FAILED===");
		
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
}
