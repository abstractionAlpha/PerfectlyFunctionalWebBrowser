package com.abstractionalpha.web.browser.perfectly_functional_web_browser;

import java.util.concurrent.LinkedBlockingQueue;

import com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.Position;
import com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.list.positional.PositionalLinkedList;
import com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.list.positional.PositionalList;

/**
 * Thread pool class.
 * 
 * @author abstractionAlpha
 *
 */
public class ThreadHandler {
	
	private static ThreadHandler instance;
	
	private volatile LinkedBlockingQueue<Thread> waiting;
	
	private volatile PositionalList<PerfectlyFunctionalWebThread> running;
	
	private volatile int capacity;
	
	private static final int DEFAULT_CAPACITY = 5;
	
	private ThreadHandler(int capacity) {
		this.capacity = capacity;
		waiting = new LinkedBlockingQueue<Thread>();
		running = new PositionalLinkedList<PerfectlyFunctionalWebThread>();
		new ThreadExecutor();
	}
	
	private ThreadHandler() {this(DEFAULT_CAPACITY);}
	
	public void add(Thread t) {
		waiting.add(t);
	}
	
	public void add(Runnable r) {
		Thread t = new Thread(r);
		add(t);
	}
	
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
				 * If PFWB is running, we can handle new requests.
				 * If the running list is full, then we can skip to the next run.
				 * Our threads are self-aware and will remove themselves when finished.
				 */
				if (running.size() < capacity) {
					curr = waiting.poll();
					
					if (curr != null) {
						new PerfectlyFunctionalWebThread(curr);
					}
					
				}
				
			}
		}
		
	}
	
	private class PerfectlyFunctionalWebThread extends Thread {
		
		private Thread runnable;
		
		private Position<PerfectlyFunctionalWebThread> pos;
		
		public PerfectlyFunctionalWebThread(Thread runnable) {
			this.runnable = runnable;
			run();
		}
		
		public void run() {
			pos = running.addFirst(this);
			runnable.run();
			try {
				runnable.join();
			} catch (InterruptedException e) {}
			running.remove(pos);
		}
		
	}
	
	// TODO implement a custom wrapper that makes takes anything runnable and adds generic handling.
	// This way, we can make a PLL using the objects we're running.

}
