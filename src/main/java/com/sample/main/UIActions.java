package com.sample.main;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sample.global.Global;

public class UIActions {
	static ExtentReports reports = new ExtentReports(Global.reportPath, true);
	static ExtentTest test = reports.startTest("SampleTestcase");
	static WebDriver driver;
	 Dimension size;
	 static String destDir;
	 static DateFormat dateFormat;
	
	public static void Action(WebDriver _driver,String objectType,String objectVal,String actionDesc,String action){
		driver = _driver;
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
				test.log(LogStatus.INFO, actionDesc);
				break;
			case "ClickBack":
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
				test.log(LogStatus.INFO, actionDesc);
				break;
			case "validate":
				test.log(LogStatus.INFO, actionDesc);
				if(driver.findElement(uiElement).getText().equalsIgnoreCase("ebay")){
					test.log(LogStatus.PASS, "Value matched");
				}
				else{
					test.log(LogStatus.FAIL, "Value not matched");
					takeScreenShot();
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
	public static void takeScreenShot() {
		  destDir = Global.screenShotPath;
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   FileUtils.copyFile(scrFile, new File(destDir + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }


}
