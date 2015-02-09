package com.rise.pool;

import java.util.concurrent.ThreadFactory;

import com.core.Queue;
import com.ds.template.impls.QueueImpl;

public class ThreadPool<T> implements Pool {
	private final int workerMax = 2;

	private Queue<Runnable> workerQueue = new QueueImpl<Runnable>(2,
			"workerQueue");

	private Queue<Runnable> taskQueue = new QueueImpl<Runnable>(5, "taskQueue");

	private static final String groupName = "Thread-Group";

	private static int groupCounter = 0;

	public ThreadPool() {
		groupCounter++;
	}

	ThreadFactory threadFactory = new ThreadFactory() {

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(new ThreadGroup(groupName + "_" + groupCounter),
					r);
		}
	};

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

	private final class Worker implements Runnable {
		Runnable firstTask;
		Thread thread;

		public Worker(Runnable task) {
			super();
			this.firstTask = task;
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
