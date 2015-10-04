package com.hc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HackerEarthMain<V, O> {
	private static final ExecutorService service = Executors
			.newFixedThreadPool(3);
	private CountDownLatch counter = null;
    Integer[] array = new Integer[10];
	private Ilogic<V, O> logic = new Ilogic<V,O>() {

		@Override
		public O compute(V v) {
			return null;
		}
		
	};
	
	public HackerEarthMain() {
	}

	private void submitTask() throws IOException {
		List<HackerEarthTask<V, O>> taskList = new ArrayList<>();

		Integer taskCounter = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String noOfTestCase = br.readLine();
		int noOfTC = Integer.parseInt(noOfTestCase);
		
		System.out.println("looping " + noOfTC);

		for (int i = 0; i < noOfTC; i++) {
			String noOfCaseString = br.readLine();
			int noOfCase = Integer.parseInt(noOfCaseString);
			System.out.println("no of actual tc " + noOfCase);

			try {
				String line = br.readLine();
				String[] inputs = line.split(" ");
				for (String input : inputs) {
					taskList.add((HackerEarthMain<V, O>.HackerEarthTask<V, O>) new HackerEarthTask<>(
							"input::" + input + "@counter" + taskCounter++));
					// taskList.add((HackerEarthTask<K, V, M>) new
					// HackerEarthTask<>("Task", input + "::" + taskCounter++ ,
					// "dd"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}

		br.close();
		counter = new CountDownLatch(taskCounter);
		List<Future<V>> futures = new ArrayList<>();

		for (HackerEarthTask<V, O> task : taskList) {
			futures.add(service.submit(task));
		}

		try {
			counter.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
			e.printStackTrace();

		}
		// task completed
		// we need to sort out the result

		for (Future<V> future : futures) {
			try {
				V v = future.get();
				System.out.println(v);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		service.shutdown();
	}

	public static void main(String[] args) throws IOException {
		HackerEarthMain<String, String> mainTask = new HackerEarthMain<>();
		mainTask.submitTask();
	}

	// private Future<>
	public class HackerEarthTask<V, O> implements Callable<V> {
		private V v;
		private O o;

		public HackerEarthTask(V v) {
			super();
			this.v = v;
		}

		@Override
		public V call() throws Exception {
			// TODO Auto-generated method stub
			// Here we need to do task and sop the output
			// append "@ and task number"
			counter.countDown();
			return (V) v;
		}

		@Override
		public String toString() {
			return "HackerEarthTask [ V=" + v + "]";
		}

	}

	// logic goes here given input and output

	interface Ilogic<V, O> {
		O compute(V v);
	}
}
