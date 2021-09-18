package org.solid.strategy;

public class Manager {
	Strategy strategy;
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
		
	}

	public int operate(int a , int b) {
		return strategy.operation(a, b);
	}
	
	public static void main(String[] args) {
		Manager m = new Manager();
		Strategy add = new Add();
		Strategy multiply = new Multiply();
		m.setStrategy(add);
		System.out.println(m.operate(2, 5));
		m.setStrategy(multiply);
		System.out.println(m.operate(2, 5));
	}
}
