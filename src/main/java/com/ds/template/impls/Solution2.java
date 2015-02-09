package com.ds.template.impls;

public class Solution2 {

    static void execute(int[] elements, int size) {
        BlockingStackTest test = new BlockingStackTest(elements, size);
        new Thread(test.producer, "Producer").start();
        new Thread(test.consumer, "Consumer").start();
    }
    public static void main(String[] args) {
    	int[] ele = {1,2};
		execute(ele, 2);
	}

private static class BlockingStackTest {

    final Producer producer;
    final Consumer consumer;

    public BlockingStackTest(int[] producerTasks, int size) {
        BlockingStack stack = new BlockingStack(size);
        producer = new Producer(stack, producerTasks);
        consumer = new Consumer(stack, producerTasks.length);
    }

    /**
    * Implements a Blocking Stack in LIFO manner.
    * The queue take n as an input to create an internal queue of size n tasks.
    */
    private class BlockingStack {
        final int size;
        final int[] tasks;
        volatile int idx;

        public BlockingStack(int size) {
            this.size = size;
            this.tasks = new int[size];
        }

        /**
        * Adds a new task to the end of the stack.
        * The function blocks if the stack is full making sure the function returns only after adding the task.
        */
        public synchronized void add(int task) {
            while (idx == size) {
                try {
                	System.out.println("stack is full waiting");
                	this.wait();
                	this.notifyAll();
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("stack adding task "+ task +"index"+idx);
            tasks[idx++] = task;
            this.notifyAll();
        }

        /**
        * Removes a task from the end of the stack.
        * The function blocks if the stack is empty making sure the function returns only after removing the task.
        */
        public synchronized int remove() {
        	while(idx == 0 ){
        		try {
        			System.out.println("conumer waiting");
					this.wait();
					this.notifyAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		this.notify();
        	}
        	System.out.println("removing index "+(idx-1) +" element" +tasks[idx-1]);
        	this.notifyAll();
        	return tasks[--idx];
        	/*
        	
            if (idx > 0) {
            	return tasks[idx--];
//                this.notify();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        */}
    }

    private class Producer implements Runnable {
        final BlockingStack stack;
        final int[] tasks;

        Producer(BlockingStack stack, int[] tasks) {
            this.stack = stack;
            this.tasks = tasks; 
        }

        public  void run() {
        	int i =0;
        	while(i<100){
            for (int task : tasks) {
                stack.add(task);
                if (task < 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            i++;
        	}
        }
    }

    private class Consumer implements Runnable {
        final BlockingStack stack;
        final int size;

        Consumer(BlockingStack stack, int size) {
            this.stack = stack;
            this.size = size;
        }

        public  void run() {
            int i = 0;
            while (i < 100) {
                System.out.print("removed" +stack.remove());
                i++;
                if (i < size) {
                    System.out.print(",");
                }
            }
        }
    }
}


}
