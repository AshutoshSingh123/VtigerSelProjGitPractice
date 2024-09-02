package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;

//next line is not required when we run the script using xml suite file
//please refer the xml file for more info
//@Listeners(com.vtiger.crm.listenerutility.ListenerImplementationClass.class)
public class InvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoice Test");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step1");
		System.out.println("step2");
		
		System.out.println("step3");
		System.out.println("step4");
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("create invoice with contact test");
		System.out.println("step1");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
	}
}
