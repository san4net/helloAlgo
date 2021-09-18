package com.ds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.ds.template.impls.heap.SimpleHeap;

public class SimpleHeapTest {

	
	@Test
	public void testInsert() {
		SimpleHeap simpleHeap = new SimpleHeap();
		simpleHeap.insert(1);
		assertEquals(1, simpleHeap.getSize());
	}


	/**
	 * Connect n ropes with minimum cost
	 *
	 * For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
	 * 1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
	 * 2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
	 * 3) Finally connect the two ropes and all ropes have connected.
	 *
	 * Total cost for connecting all ropes is 5 + 9 + 15 = 29.
	 *
	 */
	@Test
	public void testMinimumCostOfConnectingnRopes() {
		SimpleHeap simpleHeap = new SimpleHeap();
		simpleHeap.insert(4);
		simpleHeap.insert(3);
		simpleHeap.insert(2);
		simpleHeap.insert(6);
		assertEquals(4, simpleHeap.getSize());
		
		int sum =0;
		int t = 0;

		while(simpleHeap.getSize() > 1) {
			int first = simpleHeap.pool();
			int second = simpleHeap.pool();
			int localSum = first + second;
			sum = sum + localSum;
			simpleHeap.insert(localSum);
		}

		
		assertEquals(29, sum);
	}
}
