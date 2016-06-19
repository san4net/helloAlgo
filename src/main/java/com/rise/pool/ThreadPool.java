package com.rise.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

import com.ds.template.Queue;
import com.ds.template.impls.QueueImpl;

public class ThreadPool<T> implements Pool {
	private final int workerMax = 2;

	private Queue<Runnable> workerQueue = new QueueImpl<Runnable>(2, "workerQueue");

	private Queue<Runnable> taskQueue = new QueueImpl<Runnable>(5, "taskQueue");

	private static final String groupName = "Thread-Group";

	private final ThreadFactory threadFactory;

	public ThreadPool() {
		this(Executors.defaultThreadFactory());
	}

	public ThreadPool(ThreadFactory th) {
		threadFactory = th;
	}

	public boolean start() throws NotInitialized {
		return true;
	}

	public void submit(Runnable task) {
		if (underPoolSize()) {
			Thread t = addThread(task);
			t.start();
		} else {
			taskQueue.enqueue(task);
		}
	}
	
	public void submit(Callable task) {
		if (underPoolSize()) {
			FutureTask futureTask = new FutureTask<>(task);
			taskQueue.enqueue(futureTask);
		} 
	}

	private Thread addThread(Runnable task) {
		Worker w = new Worker(task);
		Thread t = threadFactory.newThread(w);
		w.thread = t;
		System.out.println("Adding Thread:" + t);
		workerQueue.enqueue(w);
		return t;
	}

	private synchronized boolean underPoolSize() {
		return (workerQueue.size() < workerMax);
	}

	public boolean shutdown() throws ShutDownException {
		return false;
	}

	private class RunnableAdapter<T> implements Runnable{
		private Callable call;
		public RunnableAdapter(Callable<T> call) {
			this.call = call;
		}
		@Override
		public void run() {
			try {
				Object call2 = call.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private final class Worker implements Runnable {
		Runnable firstTask;
		ThreadFactory threadFactory;
		Thread thread ;
		
		public Worker(Runnable task) {
			super();
			this.firstTask = task;
			this.thread = threadFactory.newThread(this);
		}

		@Override
		public void run() {
			try {
				Runnable task = firstTask;
				firstTask = null;
				while (task != null || (task = taskQueue.dequeue()) != null) {
					runTask(task);
					task = null;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				//
			}
		}

		private void runTask(Runnable task) {
			System.out.println(thread.getThreadGroup() + thread.getName());
			task.run();
		}
	}

}
