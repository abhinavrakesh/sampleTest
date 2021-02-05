package com.sample.initsteup;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.global.Global;
import com.sample.main.ExcelFile;
import com.sample.main.ExcelFileAction;
import com.sample.main.UIActions;
import com.sample.utility.SampleDesigerCapability;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumInitialization {
	public static WebDriver  driver;
	static DesiredCapabilities desiredCapabilities;
	static AppiumServiceBuilder builder;
	static AppiumDriverLocalService service;
	public static void init(SampleDesigerCapability sdc) {
		System.out.println(sdc.getAppiumLocalHost()); 
		startServerAppium(sdc);
		desiredCapabilities = DesiredCapabilities.android();
		desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, sdc.getPlatform());
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, sdc.getDeviceName());
		desiredCapabilities.setCapability(MobileCapabilityType.VERSION, sdc.getOsVersion());
		desiredCapabilities.setCapability("appPackage", sdc.getAppPackage());
		desiredCapabilities.setCapability("appActivity", sdc.getAppActivity());
		ExcelFile excelFile;
		ExcelFileAction excelFileAction;
		try {
			driver = new AndroidDriver(new URL(sdc.getAppiumLocalHost()),desiredCapabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			excelFile = new ExcelFile(Global.excelPath);
			excelFileAction = new ExcelFileAction(Global.excelPathAction);
			int rowCount = excelFile.getRowCount(0);
			String[] dataObject,dataAction;
			for (int i = 1; i < rowCount; i++) {
				dataObject=excelFile.getData("objects", i, 0);
				dataAction=excelFileAction.getData("actions", i, 0);
				UIActions.Action(driver,dataObject[1].trim(),dataObject[2].trim(),dataAction[0].trim(),dataAction[1].trim());
			}

		} catch (Exception e) {
			// TODO: handle exception
		} 
		finally {
			stopServerAppium();
		}

	}
	public static void startServerCMD() {


		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void stopServerCMD() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void startServerAppium(SampleDesigerCapability sdc) {
	    //Build the Appium service
	    builder = new AppiumServiceBuilder();
	    builder.withIPAddress(sdc.getIpAddress());
	    builder.usingPort(Integer.parseInt(sdc.getPort()));
	    builder.withCapabilities(desiredCapabilities);
	    builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
	    builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

	    //Start the server with the builder
	    service = AppiumDriverLocalService.buildService(builder);
	    service.start();
	    System.out.print("Server start");
	}

	public static void stopServerAppium() {
		System.out.print("Server stop");
	    service.stop();
	}
	

}
