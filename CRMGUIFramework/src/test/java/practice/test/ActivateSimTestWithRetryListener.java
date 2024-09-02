package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;

public class ActivateSimTestWithRetryListener {
	
	@Test(retryAnalyzer = com.vtiger.crm.listenerutility.RetryListenerImplementation.class)
	public void activateSim() {
		System.out.println("execute activateSim Test");
		Assert.assertEquals("", "Login");
		System.out.println("step1");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
	}
	
}
