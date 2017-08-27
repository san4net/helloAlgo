package com.java8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsTest {

	public static void pythoTheoram(){
		Stream<double[]>  doubleStream =
				IntStream.rangeClosed(1, 100)
		.boxed()
		.flatMap(a->IntStream.rangeClosed(a, 100)
				.mapToObj(b-> new double[]{a,b,Math.sqrt(a*a+b*b)})
				.filter(t -> t[2]%1==0));
		
		doubleStream.forEach(d -> System.out.println(d[0]+":"+d[1]+":"+d[2]));
	}
	
	public static void main(String[] args) {
		pythoTheoram();
	}
	
}
