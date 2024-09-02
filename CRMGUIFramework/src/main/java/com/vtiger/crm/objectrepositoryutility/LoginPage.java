package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

/**
 * contains login page elements and business library like login()
 * @author Ashutosh
 * 
 * 
 */
public class LoginPage extends WebDriverUtility{  //rule1- create separate java class
						  //rule2- Object creation
	WebDriver driver;
	
	@FindBy(name="user_name")
	private WebElement unEdt;
	
	@FindBy(name="user_password")
	private WebElement pwdEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
							//rule3- object initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

							//rule4-object encapsulation
	
	public WebElement getUnEdt() {
		return unEdt;
	}

	public WebElement getPwdEdt() {
		return pwdEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
							//provide action
	/**
	 * login to app based on username and password
	 * 
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password) {
		waitForPageLoad(driver);
		driver.manage().window().maximize();
		unEdt.sendKeys(username);
		pwdEdt.sendKeys(password);
		loginBtn.click();
	}
	/**
	 * login to app based on username,password and url
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		waitForPageLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		unEdt.sendKeys(username);
		pwdEdt.sendKeys(password);
		loginBtn.click();
	}
}
