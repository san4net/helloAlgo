package org.solid.command.example2;

public class App {
	
	public static void main(String[] args) {
		Algorithm algorithm = new Algorithm();
		
		new Thread(()->{
			algorithm.produce();
		}).start();;
		
		new Thread(()->{
			algorithm.consume();
		}).start();;
		
		
		
		}

}
