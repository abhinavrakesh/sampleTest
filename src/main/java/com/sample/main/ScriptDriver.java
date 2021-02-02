package com.sample.main;

import com.sample.initsteup.AppiumInitialization;
import com.sample.utility.SampleJsonParser;

public class ScriptDriver {
	public static AppiumInitialization appiumInit;
	static SampleJsonParser sampleJsonParser;
	public static void main(String[] args) {
		sampleJsonParser = new SampleJsonParser();
		sampleJsonParser.parseJson();
		appiumInit = new AppiumInitialization();
		appiumInit.init(sampleJsonParser.parseJson());
	}

}
