package com.vzw.ccpa.page;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.vzw.ccpa.test.TestClass;
import com.vzw.pos.automation.drivers.BasePage;


//import com.vzw.pos.automation.drivers.BasePage;

public class LoginPage extends BasePage {
	
	
	private WebDriver driver;
	@FindBy(xpath="//*[@id='login']")
	WebElement frstusrname;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement frstPass;
	
	@FindBy(xpath="//*[@id='Log_On']")
	WebElement loginButton;
	
	@FindBy(xpath="//*[@id='IDToken1']")  //*[@id='IDToken1']
	WebElement Secondusrname;
	
	@FindBy(xpath="//*[@id='IDToken2']")
	WebElement Scondpswd;
	
	@FindBy(xpath="//*[@id='login-submit']")
	WebElement SignIn;
	
	@FindBy(xpath="//*[@id=\'IDToken1\']")
	WebElement txtsecretename;
	
	@FindBy(xpath="//*[@id='otherButton']")
	WebElement clickon_continue; 
	

	
	Hashtable<String, String> hTable;
	
	public LoginPage(WebDriver driver,Hashtable<String, String> table) {//constructor
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.hTable=table;
	}
	
	
	@SuppressWarnings("unchecked")
	public void applicationLogin() throws FileNotFoundException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		SoftAssert softAssert = new SoftAssert();
		
		   
		setElement(driver,frstusrname,hTable.get("Frst_User"),"Pass: user able to enter username","Fail User unable to enter username");
		setElement(driver,frstPass,hTable.get("First_Pass"),"Pass:user able to enter password","Fail user unable to enter password");
		clickonElement(driver,loginButton,"Pass: user able to click on login button","Fail not able to click login ");

		
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title ='sign_in']")));
		driver.switchTo().frame(element);
		Thread.sleep(2000);
		String str = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-content\"]//h1"))).getText();
		softAssert.assertEquals("Sign in",str);
		System.out.println("Pass:signin page verified"+str);
		
			
		
		try {
			
			List<WebElement> elementsxpath1 = driver.findElements(By.cssSelector("#main-content"));
			for(int i=0; i<elementsxpath1 .size(); i++) {
			    System.out.println("printingmessage"+elementsxpath1);
			}
		setElement(driver,Secondusrname,hTable.get("MTN"),"pass user able to enter MDN","Fial:User unable enter MDN number");
		setElement(driver,Scondpswd,hTable.get("Pass"),"Pass user able to ener password","Fail user unbale to enter password");
		clickonElement(driver,SignIn,"Pass: user ablt to click Sign in button","Fail: user unable to click Sign in button");
		//setElement(driver,txtsecretename,hTable.get("Secrete"),"Pass user able to enter secret answer","Fail: user not able to enter secrete answer");
		//clickonElement(driver,clickon_continue,"Pass: user able to click on continue button","Fail: user unable to click on continue button");
	//	String str2 = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div[1]/p[1]/text()")).getText();
	//	System.out.println("Pass:Secrete Question/Answer Page verified"+str2);
	//	softAssert.assertEquals("We don't recognize the device you're using. Please verify your User ID, then answer the secret question.",str2);
       // String str3 =driver.findElement(By.cssSelector("#accordion__heading-accountinfo > span.sc-dUjcNx.jWNMGb")).getText();
     //   System.out.println("Pass:PersonalInformation verified"+str3);	
		 //softAssert.assertAll();

		}catch(Exception e) {
			e.printStackTrace();
			captureScreenshotPage((Map<String, String>) driver,"page");	

		}
		driver.switchTo().parentFrame();
		
	}
	
	
	
	
}

