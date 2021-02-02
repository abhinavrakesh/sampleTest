package com.sample.main;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sample.global.Global;

public class UIActions {
	static ExtentReports reports = new ExtentReports(Global.reportPath, true);
	static ExtentTest test = reports.startTest("SampleTestcase");
	
	
	public static void Action(WebDriver driver,String objectType,String objectVal,String action){
		try {
			
			@SuppressWarnings("unused")
			By uiElement=null;
			switch (objectType) {
			case "ID":
				uiElement = By.id(objectVal.trim());
				break;
			case "NAME":
				uiElement = By.name(objectVal);
				break;
			case "CLASSNAME":
				uiElement = By.className(objectVal);
				break;
			case "CSS":
				
				break;
			case "XPATH":
				uiElement = By.xpath(objectVal);
				break;
				
			default:
				break;
			}
			
			
			switch (action) {
			case "Click":
				driver.findElement(uiElement).click();
				test.log(LogStatus.INFO, "Clicked on button");
				break;
			case "ClickBack":
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
				test.log(LogStatus.INFO, "Clicked on back button");
				break;
			case "validate":
				if(driver.findElement(uiElement).getText().equalsIgnoreCase("ebay")){
					test.log(LogStatus.PASS, "Value matched");
				}
				else{
					test.log(LogStatus.FAIL, "Value not matched");
				}
				break;
				
			default:
				
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			reports.endTest(test);
			reports.flush();
		}

	}
	

}
