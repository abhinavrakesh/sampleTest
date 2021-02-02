package com.sample.utility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sample.global.Global;

public class SampleJsonParser {
	public SampleDesigerCapability sdc = new SampleDesigerCapability();
	public SampleDesigerCapability parseJson() {
		try {
			Object obj = new JSONParser().parse(new FileReader(Global.desiredCapabilitiesJSON));
			// typecasting obj to JSONObject 
	        JSONObject jo = (JSONObject) obj; 
	        JSONObject dc = (JSONObject) jo.get("desiredCapabilities"); 
	          
	        sdc.setAppiumLocalHost(dc.get("APPIUM_LOCAL_HOST").toString());
	        sdc.setPlatform(dc.get("PLATFORM").toString());
	        sdc.setDeviceName(dc.get("DEVICE_NAME").toString());
	        sdc.setAppPackage(dc.get("APP_PACKAGE").toString());
	        sdc.setAppActivity(dc.get("APP_ACTIVITY").toString());
	        sdc.setOsVersion(dc.get("OS_VERSION").toString());
	        sdc.setIpAddress(dc.get("withIPAddress").toString());
	        sdc.setPort(dc.get("usingPort").toString());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdc; 
	}

}
