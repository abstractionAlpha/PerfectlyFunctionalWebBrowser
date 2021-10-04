package com.abstractionalpha.web.browser.perfectly_functional_web_browser;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Thread pool class. Implemented using an ArrayBlockingQueue.
 * 
 * @author Mason
 *
 */
public class ThreadHandler {
	
	private static ThreadHandler instance;
	
	private ThreadHandler() {}
	
	public static ThreadHandler getInstance() {
		if (instance == null)
			instance = new ThreadHandler();
		
		return instance;
	}
	
	public static ThreadHandler getInstance(int capacity) {
		if (instance == null)
			instance = new ThreadHandler(capacity);
		
		return instance;
	}

}
