package com.abstractionalpha.web.browser.perfectly_functional_web_browser;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread pool class.
 * 
 * @author abstractionAlpha
 *
 */
public class ThreadHandler {
	
	private static ThreadHandler instance;
	
	private volatile LinkedBlockingQueue<Thread> waiting;
	
	private volatile ArrayBlockingQueue<Thread> running;
	
	private volatile int capacity;
	
	private static final int DEFAULT_CAPACITY = 5;
	
	private ThreadHandler(int capacity) {
		waiting = new LinkedBlockingQueue<Thread>();
		running = new ArrayBlockingQueue<Thread>(capacity);
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
		
		public ThreadExecutor() {run();}
		
		/**
		 * Uses polling to wait for something to be in the queue... again... and again... until the queue is no more
		 */
		public void run() {
			
			Thread curr;
			
			while (PerfectlyFunctionalWebBrowser.isRunning()) {
				
				/**
				 * Case 1: Updating runqueue.
				 * If the front thread is no longer alive, it will be removed.
				 * After this, we will skip the remaining run of this loop.
				 * This way we can loop indefinitely until all completed queues are finished.
				 * 
				 * TODO: Replace the running queue with a positional linked list.
				 * That way, a thread can remove itself from the PLL when it is finished running.
				 */
				if (!running.peek().isAlive()) {
					curr = running.remove();
					continue;
				}
				
				/**
				 * Case 2: Adding to runqueue.
				 * If there is space in the runqueue, then we can just add something to it.
				 * If the runqueue is full or there's nothing in the waiting queue then we do not care.
				 */
				if (running.size() < capacity) {
					curr = waiting.poll();
					
					if (curr != null) {
						curr.run();
						running.add(curr);
					}
					
				}
				
			}
		}
		
	}

}
