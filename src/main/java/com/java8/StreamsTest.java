package com.java8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
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
	private static Map<String, Long> freqMap = new HashMap<>();

	public static void frequencyMap(){

		Stream<String> words = Stream.of("map","cap","CAP","tap","wap","WAP");
		 //style 1
		words.forEach((String word)->{freqMap.merge(word.toLowerCase(),1l, Long::sum);});
				System.out.println(freqMap);

		freqMap = words.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

		System.out.println(freqMap);
	}

	public static void topTen(){
		List<String>  topTen = freqMap.keySet().stream().sorted(Comparator.comparing(freqMap::get).reversed()).limit(10).collect(Collectors.toList());
	}

	public void topAlbum(){
		Stream<Album> albumList = null;

		Map<Artist, Album> topHits = albumList.collect(Collectors.toMap(Album::getArtist, a->a, BinaryOperator.maxBy(Comparator.comparing(Album::getSales))));
	}
	
	public static void main(String[] args) {
		pythoTheoram();
		frequencyMap();
	}

	public static class Album{
		Artist artist;
		double sales;

		public Album(Artist artist, double sales) {
			this.artist = artist;
			this.sales = sales;
		}

		public Artist getArtist() {
			return artist;
		}

		public double getSales() {
			return sales;
		}


	}

	public static class Artist{

	}
	
}
