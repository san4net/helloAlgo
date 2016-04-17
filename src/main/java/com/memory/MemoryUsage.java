package com.memory;

public class MemoryUsage {

	public static long getBytesUsingPrimitives(int n) {
		System.gc(); 
		long memStart = Runtime.getRuntime().freeMemory();
		double[] a = new double[n];
		// put some random values in the matrix
		for (int i = 0; i < n; ++i) {
			a[i] = Math.random();
		}

		long memEnd = Runtime.getRuntime().freeMemory();
		return memStart - memEnd;
	}

	public static long getBytesUsingRefernce(int n) {
		System.gc(); // force garbage collection
		long memStart = Runtime.getRuntime().freeMemory();
		Double[] a = new Double[n];
		// put some random values in the matrix
		for (int i = 0; i < n; ++i) {
				a[i] = (Math.random());
		}
		long memEnd = Runtime.getRuntime().freeMemory();
		return memStart - memEnd;
	}

	public static void main(String[] args) {
		long i = getBytesUsingPrimitives(100000);
		System.out.println("memory for 100000 " + i + "\n byte per" + (i / 100000));
		long j = getBytesUsingRefernce(1000);
		System.out.println("meme" + j +"per "+ (j/1000));
		new MemoryUsage().createStackOverFlow(new Object());
		
	}
	
	private void createStackOverFlow(Object poison){
		if(poison != null) createStackOverFlow(poison);
	}
}
