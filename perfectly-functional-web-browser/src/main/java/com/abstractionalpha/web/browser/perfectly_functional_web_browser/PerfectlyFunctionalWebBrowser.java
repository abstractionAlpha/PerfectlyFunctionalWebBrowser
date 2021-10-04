package com.abstractionalpha.web.browser.perfectly_functional_web_browser;

public class PerfectlyFunctionalWebBrowser {
	
	private static boolean running = false;
	
	public static void main(String[] args) {
		// TODO insert startup logic
		running = true;
		
		running = false;
	}
	
	public static boolean isRunning() {
		return running;
	}

}
