package com.java8;

import static org.hamcrest.CoreMatchers.either;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLamda {
/**
 *  Using the listFiles(FileFilter) and isDirectory methods of the java.io.File class, 
 *  write  a  method  that  returns  all  subdirectories of  a  given  directory. 
 *   Use  a lambda  expression  instead  of  a FileFilter  object.  Repeat  with  a 
 *    method expression. 
 * 
 */
	public void lamdaTest1() {
		File f = new File(".");
		// jdk <8
		Arrays.asList(f.listFiles()).forEach(new Consumer<File>() {
			@Override
			public void accept(File t) {
				System.out.println(t);
			}
		});
		/**
		 *  jdk8> lamdas
		 *  way of passing Consumer
		 */
		Arrays.asList(f.listFiles()).forEach(System.out::println);
		/**
		 *  jdk8> lamdas
		 *  way of passing FileFilter 
		 */
		
		File[] directories = f.listFiles(e -> e.isDirectory());
		// print those directories
		Arrays.asList(directories).forEach(System.out::println);

		/**
		 *  jdk8> lamdas method expression
		 * =
		 * 
		 * 
		 * 
		 * 
		 * ''way of passing FileFilter 		 */
		File[] directoriesWithMethod = f.listFiles(File::isDirectory);
		
	}

	/**
	 * . Using the list(FilenameFilter) method of the java.io.File class, write a method that returns 
	 * all files in a given directory with a given extension. Use a lambda expression, not a FilenameFilter.
	 *  Which variables from the enclosing scope does it capture? 
	 * @param args
	 */
	
	public void lamdaTest2(){
		File f = new File(".");
		File[] fileWithTxt =  f.listFiles((ff,e)-> e.endsWith("txt"));
		Arrays.asList(fileWithTxt).forEach(System.out::println);
		
	}
	private Set<String> loadFromQAS(Set<String> input){
		return null;
	}
	
	private Set<String> cache = new HashSet<>();
	public void streamTest(){
		Stream<Double> a = Stream.generate(Math::random).limit(10);
		
		cache.add("santosh");
		List<String> list = Arrays.asList("santosh","pankaj", "khushboo", "aniket", "khush");
		ArrayList<String> rr = list.stream().filter((s)->{return s.equals("santosh");}).
				collect(ArrayList<String>::new, 
				ArrayList<String>::add,
			    (c,d)->{});
		
		List<String> rr2 = list.stream().filter((s) -> {
			return s.equals("santosh");
		}).collect(Collectors.toList());
		
		System.out.println(rr2);
		System.out.println(System.identityHashCode(rr2));
		
		// 
		
		
	/*	Set<String> a = list.stream().filter((e)->!cache.contains(e)).collect(Collectors.toSet());
		a.stream().forEach(System.out::println);*/
		
		/*long i = list.parallelStream().filter((s)->s.length()>5).count();
		System.out.println(i);
		Stream s = Stream.generate(()->"sorry");
		System.out.println(s.toString());
		Stream<Integer> numbers =  Stream.of(1,2,3);
		Optional<Integer> a = numbers.reduce(Integer::sum);
		System.out.println(a.get());*/
//		list.stream().collect(collector)
//		list.stream().collect(collector);
	}
	public static void main(String[] args) {
		TestLamda tl = new TestLamda();
//		tl.lamdaTest1();
//		tl.lamdaTest2();
		tl.streamTest();
	}
	
	
	 static class LimitedLL implements Iterable<String>{
		Node head;
		
		 
		@Override
		public Iterator<String> iterator() {
			return null;
		}
		
		public void add(Node data){
			
		}
		
	   private void remove(){
		   
	   }
	}

	static class Node{
		Date time;
		int batchSize;
		Duration timeTaken;
		
	}
}
