package com.vzw.ccpa.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.vzw.ccpa.page.LoginPage;
import com.vzw.ccpa.page.VpdDownloadPIMultiLIne;
import com.vzw.ccpa.page.VpdLandingpagePreview;
import com.vzw.pos.automation.constants.FailureConstants;
import com.vzw.pos.automation.drivers.AbstractClass;
import com.vzw.pos.automation.exceptions.TapsiumCatchBlock;
import com.vzw.pos.automation.exceptions.TapsiumFinalBlock;
import com.vzw.pos.automation.utilities.TestDataProvider;

public class TestClass<initiateBrowserFromGrid> extends AbstractClass implements ITest{

	private Logger logger = LoggerFactory.getLogger(TestClass.class);
	Hashtable<String, String> hTable;
	@Factory(dataProviderClass = TestDataProvider.class, dataProvider = "testScenarios")
	public TestClass(Hashtable<String, String> table) {
		this.dataTable = table;
	}

	@BeforeTest
	public void init() throws IOException {
		Datattest.CreateResultsSummary("");
	}
	@Test	
	public void testExecution() throws Exception {
		try { 
			
		//initiateBrowserFromGrid("chrome","https://www.vpd.com/");
		System.setProperty("webdriver.chrome.driver", "C:\\ccpa\\Drivers\\chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
        driver.get("https://www98.verizon.com/privacy/secure/your-data");
       // dataTable.get(Url);
      //  System.out.println("Wait a bit for the page to render");
        
	     /*  ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank)');");
	        
	       ArrayList<String> tabs= new ArrayList<>(driver.getWindowHandles());
	       driver.switchTo().window(tabs.get(1));
	       driver.get("https://inka.sdc.vzwcorp.com:7002/amtools/login.jsp");
	       driver.switchTo().window(tabs.get(0));	*/
	      
		//initiateBrowserFromGrid("Browser","Url");
		LoginPage pagelogin=new LoginPage(driver,dataTable); 
		pagelogin.applicationLogin();
		
		//This is for landing VPD Page 			
		
		System.out.println("Pass: Your");
		VpdLandingpagePreview land=new VpdLandingpagePreview(driver,dataTable);
		
		land.vPdLand();
		
		//VpdDownloadPIMultiLIne download=new VpdDownloadPIMultiLIne(driver,dataTable);
		//download.vPDown();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", js);
		
		//WebElement element = driver.findElement(By.cssSelector("#accordion__panel-accountinfo > div"));
	   // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		
		 TimeUnit.SECONDS.sleep(3);
		driver.quit();
			
		}catch (Throwable e) { 
			TapsiumCatchBlock tapCatchBlock = new TapsiumCatchBlock(getDriver(), testProperties);
				tapCatchBlock.failureBlock(getDriver(), e, testProperties,
						FailureConstants.ERROR); logger.error("Exception : ",e); } finally {
							dataTable.put("jsessionId", returnJsessionID(getDriver())); TapsiumFinalBlock
							tapFinalBlock = new TapsiumFinalBlock(getDriver(), testProperties);
							tapFinalBlock.captureFinalScreenshot(getDriver(), testProperties);
							tapFinalBlock.captureDebugLogs(fileName, testProperties);
							//ApplicationSignOut appSignOut = new ApplicationSignOut(getDriver(),testProperties, dataTable); //appSignOut.POSSignout(); tearDown(); 
							}

							//System.out.println(dataTable);
						}


		public String getTestName() {
			return dataTable.get("TCNumber").trim() + "_" + dataTable.get("ScenarioDescription");
			
		}
		
	}