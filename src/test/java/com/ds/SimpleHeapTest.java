package com.ds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ds.template.impls.heap.SimpleHeap;

public class SimpleHeapTest {

	
	@Test
	public void testInsert() {
		SimpleHeap simpleHeap = new SimpleHeap();
		simpleHeap.insert(1);
		assertEquals(1, simpleHeap.getSize());
	}
	
	@Test
	public void testFlow() {
		SimpleHeap simpleHeap = new SimpleHeap();
		simpleHeap.insert(4);
		simpleHeap.insert(3);
		simpleHeap.insert(2);
		simpleHeap.insert(6);
		assertEquals(4, simpleHeap.getSize());
		
		int sum =0;
		int t = 0;
		while(( t = simpleHeap.pool()) > 0) {
			sum = sum + t;
		}
		
		assertEquals(29, sum);
	}
}
