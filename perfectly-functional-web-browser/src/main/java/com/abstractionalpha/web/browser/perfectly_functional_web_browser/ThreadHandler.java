package com.abstractionalpha.web.browser.perfectly_functional_web_browser;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Thread pool class. Implemented using an ArrayBlockingQueue.
 * 
 * @author abstractionAlpha
 *
 */
public class ThreadHandler {
	
	private static ThreadHandler instance;
	
	private volatile ArrayBlockingQueue<Thread> queue;
	
	private volatile int capacity;
	
	private static final int DEFAULT_CAPACITY = 5;
	
	private ThreadHandler(int capacity) {
		queue = new ArrayBlockingQueue<Thread>(capacity);
	}
	
	private ThreadHandler() {this(DEFAULT_CAPACITY);}
	
	protected static ThreadHandler getInstance() {
		if (instance == null)
			instance = new ThreadHandler();
		
		return instance;
	}
	
	protected static ThreadHandler getInstance(int capacity) {
		if (instance == null)
			instance = new ThreadHandler(capacity);
		
		return instance;
	}
	
	/**
	 * Thread executor class. Takes things out of the queue and runs them.
	 * Uses polling, because this browser sucks on purpose.
	 * 
	 * @author abstractionAlpha
	 *
	 */
	private class ThreadExecutor extends Thread {
		
	}

}
