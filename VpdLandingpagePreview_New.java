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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

import com.vzw.ccpa.test.Datattest;
import com.vzw.pos.automation.drivers.BasePage;

public class VpdLandingpagePreview extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsx;
	
	//Categories
	By AccountAndIdentity = By.xpath("//*[@id=\"accordion__heading-accountinfo\"]");
	By NetworkAndCustomerActivity = By.xpath("//*[@id=\"accordion__heading-networkactivity\"]");
	By ThirdPartyInsights = By.xpath("//*[@id=\"accordion__heading-collectedinferences\"]");
	By AddOns = By.xpath("//*[@id=\"accordion__heading-addons\"]");
	
	By ExpandedAccountAndIdentity = By.xpath("//*[@id=\"accordion__panel-accountinfo\"]");
	By ExpandedNetworkAndCustomerActivity = By.xpath("//*[@id=\"accordion__panel-networkactivity\"]");
	By ExpandedThirdPartyInsights = By.xpath("//*[@id=\"accordion__panel-collectedinferences\"]");
	By ExpandedAddOns = By.xpath("//*[@id=\"accordion__panel-addons\"]");;
	
	Hashtable<String, String> hTable;

	public VpdLandingpagePreview(WebDriver driver,Hashtable<String, String> table) throws IOException {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.hTable=table;
	}

	//vPdLand("clickon_previewplus","clickion_personalinfo");
	@SuppressWarnings("unchecked")
	public void vPdLand() throws InterruptedException, IOException {
		
		int timeOutInSeconds = 120; //2 minutes
		wait = new WebDriverWait(driver, timeOutInSeconds);
		jsx = (JavascriptExecutor) driver;
		
		List<String> subCategoryAccountAndIdentity = new ArrayList<String>();
		subCategoryAccountAndIdentity.add("Contact Information and Personal Identifiers");
		subCategoryAccountAndIdentity.add("Government identifiers");
		subCategoryAccountAndIdentity.add("Line Information");
		
		List<String> subCategoryAddOns = new ArrayList<String>();
		subCategoryAddOns.add("Verizon Cloud");

//		jsx.executeScript("window.scrollBy(0,100)");
		//Expand Account and Identity Category
		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountAndIdentity)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedAccountAndIdentity));
			System.out.println("Test Pass: Account and Identity Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Account and Identity Category does not expand");
		}
		
		//Assert Sub-category titles of Account and Identity Category
		for (int i=0; i<subCategoryAccountAndIdentity.size();i++) {
			String subCategory = subCategoryAccountAndIdentity.get(i);
			By xpath = SubCategoryXpath(subCategory);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
				System.out.println("Test Pass: Sub Category \"" + subCategory + "\" is available");
			}catch (Exception e) {
				Assert.fail("Test Fail: Sub Category \""+ subCategory + "\" is not available");
			}
		}
		
		//Expand Sub-category of Account and Identity Category
		for (int i=0; i<subCategoryAccountAndIdentity.size();i++) {
			String subCategory = subCategoryAccountAndIdentity.get(i);
			By xpath = SubCategoryXpath(subCategory);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).click();
			String bool_expand = driver.findElement(xpath).getAttribute("aria-expanded");
			Assert.assertEquals("Test Fail: Sub Category \""+ subCategory + "\" does not expand", "true", bool_expand);
			System.out.println("Test Pass: Sub Category \"" + subCategory + "\" expanded");
			if ( i == 0) {
				String s1="MTN";
				String s2 = hTable.get("MTN");
				Thread.sleep(2000);
				List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"accordion__panel-4\"]/div"));
				for (WebElement element : elements) {
					s1 = s1 + "," + element.findElement(By.tagName("span")).getText();
					s2 = s2 + "," + element.findElement(By.tagName("p")).getText();
				}
				System.out.println("S1 : " + s1);
				System.out.println("S2 : " + s2);
				Datattest.createHeader(s1);
				Datattest.updateResultsSummary2(s2);
			}
		}	
		
		//Expand Network and customer activity
		wait.until(ExpectedConditions.visibilityOfElementLocated(NetworkAndCustomerActivity)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedNetworkAndCustomerActivity));
			System.out.println("Test Pass: Network And Customer Activity Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Network And Customer Activity Category does not expand");
		}
		
		//Expand Third Party Insights
		wait.until(ExpectedConditions.visibilityOfElementLocated(ThirdPartyInsights)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedThirdPartyInsights));
			System.out.println("Test Pass: Third Party Insights Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Third Party Insights Category does not expand");
		}
		
		//Expand Add-Ons
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddOns)).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ExpandedAddOns));
			System.out.println("Test Pass: Add-Ons Category expanded");
		}catch(Exception e) {
			Assert.fail("Test Fail: Add-Ons Category does not expand");
		}
		
		//Assert Sub-category titles of Add-Ons
		for (int i=0; i<subCategoryAddOns.size();i++) {
			String subCategory = subCategoryAddOns.get(i);
			By xpath = SubCategoryXpath(subCategory);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
				System.out.println("Test Pass: Sub Category \"" + subCategory + "\" is available");
			}catch (Exception e) {
				Assert.fail("Test Fail: Sub Category \""+ subCategory + "\" is not available");
			}
		}
		
		//Expand Sub-category of Add-Ons
		for (int i=0; i<subCategoryAddOns.size();i++) {
			String subCategory = subCategoryAddOns.get(i);
			By xpath = SubCategoryXpath(subCategory);
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).click();
			String bool_expand = driver.findElement(xpath).getAttribute("aria-expanded");
			Assert.assertEquals("Test Fail: Sub Category \""+ subCategory + "\" does not expand", "true", bool_expand);
			System.out.println("Test Pass: Sub Category \"" + subCategory + "\" expanded");
		}
	}
	
	public By SubCategoryXpath(String subcategory) {
		return By.xpath("//*[text() = \""+subcategory+"\"]//parent::*[@class=\"sub_accordion_button\"]");
	}

	public void terminate() {
		driver.close();
	}
}
	

