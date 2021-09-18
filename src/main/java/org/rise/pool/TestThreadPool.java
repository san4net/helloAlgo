package org.rise.pool;


public class TestThreadPool {
	public static void main(String[] args) {
		int i =2; 
		System.out.println(i^1);
		/**
		 * 
		PropertyConfigurator.configure("C:/non_os/projects/BrushUp/src/com/rise/pool/log4j.properties");
		File f = new File("C:/non_os/projects/BrushUp/src/com/rise/pool/log4j.properties");
		System.out.println("file  santi"+ f.exists());
		ThreadPool<Runnable> tp = new ThreadPool<Runnable>();
		
		tp.submit(createTask());
		tp.submit(createTask());
		tp.submit(createTask());
		tp.submit(createTask());
		
		ThreadPool<Runnable> tp1 = new ThreadPool<Runnable>();
		tp1.submit(createTask());
		tp1.submit(createTask());
		

	*/}
	static int i=0;
	private static Runnable createTask(){
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stu
				System.out.println("task no "+i++);
				
			}
		};
		
		return task;
	}
}
