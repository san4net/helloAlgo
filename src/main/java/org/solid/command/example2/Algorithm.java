package org.solid.command.example2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import org.solid.command.Command;

public class Algorithm {
	
	BlockingQueue<Command>  commandList;

	public Algorithm() {
		super();
		this.commandList = new ArrayBlockingQueue<>(10);
	}
	
	public void produce() {
		try {
			IntStream.range(1, 11).forEach(a -> {commandList.add(new TaskSolver(new Task(a)));}
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consume() {
		
			IntStream.range(1, 11).forEach(a->{
				try {
					Thread.sleep(1000);
					commandList.take().execute();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
			});
			
			
		}
	

}
