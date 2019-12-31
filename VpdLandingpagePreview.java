package com.vzw.ccpa.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.vzw.ccpa.test.Datattest;
import com.vzw.pos.automation.drivers.BasePage;

public class VpdLandingpagePreview extends BasePage {



	private WebDriver driver;     //div[@id='accordion__heading-accountinfo']
	@FindBy(xpath="//*[@id='accordion__heading-accountinfo']/span[1]")
	WebElement clickon_previewplus;
	//@FindBy(xpath="")
	//WebElement personalinfo

	@FindBy(xpath="//*[@id='accordion__heading-4']")
	WebElement clickion_personalinfo;

	@FindBy(css="#accordion__panel-accountinfo > div")
	WebElement Privwedata;
	@FindBy(xpath="//*[@id='accordion__heading-5']")
	WebElement Government_identifiers;
	@FindBy(xpath="//*[@id='accordion__heading-6']")
	WebElement BillingNfinincial;
	@FindBy(xpath="//*[@id='accordion__heading-7']")
	WebElement TechnicalIdentifier;

	Hashtable<String, String> hTable;

	private static ArrayList<String> subCatList;

	public VpdLandingpagePreview(WebDriver driver,Hashtable<String, String> table) throws IOException {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.hTable=table;



	}

	//vPdLand("clickon_previewplus","clickion_personalinfo");
	@SuppressWarnings("unchecked")
	public void vPdLand() throws InterruptedException, IOException {
		//captureScreenshotPage((Map<String, String>) driver,"page");
		//clickonElement(driver, Clickptr1priview, "", SAPB2BArea);
		//try {
		subCatList=new ArrayList<String>();
		subCatList.add("Contact Information and Personal Identifiers");
		subCatList.add("Government identifiers");
		subCatList.add("Billing and Financial Information");
		subCatList.add("Technical identifiers");
		

		SoftAssert softAsseRt = new SoftAssert();
			

		try {

			String str00=driver.findElement(By.xpath("//*[@id='root']/div[3]/div/div[2]/div/h2[1]")).getText();
			softAsseRt.assertEquals("Your personal information", str00);

			System.out.println("Pass: Your personal informagiton is exist in vpd landing page"+str00);

			System.out.println("Pass: Preview dashboard page your personla information text exist");
			
            Thread.sleep(7000);
			WebElement scrol = driver.findElement(By.xpath("//*[contains(@id,'accordion__heading-accountinfo')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrol);
			//clickonElement(driver,personalinfo,"pass","fail");
			
			Thread.sleep(7000);
			clickonElement(driver,clickon_previewplus,"Pass: user able to click on accountaidentityplus sign","Fail user unale to click on accountaidentityplus sign");
			Thread.sleep(7000);
			clickonElement(driver,clickion_personalinfo,"Pass: user able to click on accountidentityplus sign","Fail: user not able to click on clickion_personalinfo");
		
		 
			//WebElement scrol2 = driver.findElement(By.xpath("//span[contains(text(),'Government identifiers')]"));
			//  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrol2);
			try {
				clickElement(driver,Government_identifiers,"Pass: user able to clcick on Government identifiers subcategories","Fail:user Unable to clcick on Government identifiers subcategories ");
			}catch(Exception exp) {
				System.out.println("We don't have this sub cat..!!");
			}
			//WebElement check = driver.findElement(By.xpath("//*[@id='accordion__heading-5"));
			//softAsseRt.assertNull(check);
			// scrol2 = driver.findElement(By.xpath("//span[contains(text(),'Billing and Financial Information')]"));
			//  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrol2);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", js);
				clickonElement(driver,BillingNfinincial,"Pass: user able to clcick on BillingNfinincial subcategories","Fail:user Unable to clcick on BillingNfinincial subcategories ");
			}
			catch(Exception exp) {
				System.out.println("We don't have this sub cat..!!");
			}
			// scrol2 = driver.findElement(By.xpath("//span[contains(text(),'Government identifiers')]"));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrol2);
			try {
				clickonElement(driver,TechnicalIdentifier,"Pass: user able to clcick on TechnicalIdentifier subcategories","Fail:user Unable to clcick on TechnicalIdentifier subcategories ");
			}catch(Exception exp) {
				System.out.println("We don't have this sub cat..!!");
			}
			try {
			//String str = driver.findElement(By.cssSelector("#accordion__panel-4")).getText();
			List<WebElement> elements = driver.findElements(By.xpath("//span[@class='sc-gFaPwZ bMusfh']"));//this will return value 
			List<WebElement> elements1 = driver.findElements(By.xpath("//p[@class='sc-jzgbtB jSkAjc']"));//this will return value 
			
			int i1=0;
			//String str1,str2,str3,str4,str5,str6;
			String s1="MTN";
			for (WebElement element : elements) {
				if(!element.getText().equalsIgnoreCase("") && !subCatList.contains(element.getText())) {
					s1=s1+","+element.getText();
					System.out.println("|"+element.getText()+"|");
				}
				i1=i1+1;
			}	
			Datattest.createHeader(s1);

			i1=0;
			//String str1,str2,str3,str4,str5,str6;
			String s=hTable.get("MTN");
			for (WebElement element : elements1) {
				
				s=s+","+element.getText();
				
				System.out.println(element.getText());
				i1=i1+1;
			}	
			Datattest.updateResultsSummary2(s);	

			//softAsseRt.assertEquals("Pass:You can update your contact information in My Verizon. message exist",str7);
			softAsseRt.assertAll();
			
			}
			catch(Exception e1) {
				e1.printStackTrace();


		}
		
			 
			 
		
	}
}


			//System.out.println(str);





		
	

