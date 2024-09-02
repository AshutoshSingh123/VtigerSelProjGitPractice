package practice.test;

import org.testng.annotations.Test;

import com.vtiger.crm.basetest.BaseClass;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author Ashutosh
 */
public class SearchContactTest extends BaseClass {
	
	/*
	 * Scenario: login()==>navigateContact==>createContact()==>verify
	 */
	@Test
	public void searchContact() {
		
		/*step1-login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
