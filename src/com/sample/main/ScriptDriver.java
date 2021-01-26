package com.sample.main;

import com.sample.initsetup.AppiumInitialization;

public class ScriptDriver {
	public static AppiumInitialization appiumInit;
	public static void main(String[] args) {
		appiumInit = new AppiumInitialization();
		appiumInit.init();
	}

}
