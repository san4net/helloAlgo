package com.ds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ds.dp.TrappingRainWater;

public class TrappingWaterTest {

	
	@Test
	public void testTap() {
		int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
		assertEquals(6, TrappingRainWater.maxWaterTapBruteForce(h));
		assertEquals(6, TrappingRainWater.maxWaterTapDp(h));
	}
}
