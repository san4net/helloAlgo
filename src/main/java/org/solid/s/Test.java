package org.solid.s;

public class Test {
	
	public Test() {
		super();
	}

	public static void main(String[] args) {
		for (int i = 1; i < 4; i++)
			for (int j = 1; j < 4; j++)
//				if (i < j)
					assert i != j : i;
		
	}
}

