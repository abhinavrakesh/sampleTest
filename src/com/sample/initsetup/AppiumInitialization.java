package com.sample.initsetup;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sample.global.Constants;
import com.sample.global.Global;
import com.sample.main.ExcelFile;
import com.sample.main.UIActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumInitialization {
	public static WebDriver  driver;
	public static void init() {
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.android();
		desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, Constants.PLATFORM);
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
		desiredCapabilities.setCapability(MobileCapabilityType.VERSION, Constants.OS_VERSION);
		desiredCapabilities.setCapability("appPackage", Constants.APP_PACKAGE);
		desiredCapabilities.setCapability("appActivity", Constants.APP_ACTIVITY);
//		driver = new AndroidDriver<WebElement>(null, null)
		ExcelFile excelFile;
		try {
			driver = new AndroidDriver(new URL(Constants.APPIUM_LOCAL_HOST),desiredCapabilities);
			waitTime();
			excelFile = new ExcelFile(Global.excelPath);
			int rowCount = excelFile.getRowCount(0);
			String[] data;
			for (int i = 0; i < rowCount; i++) {
				data=excelFile.getData("login", i, 0);
				UIActions.Action(driver,data[0].trim(),data[1].trim(),data[2].trim());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void waitTime(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
