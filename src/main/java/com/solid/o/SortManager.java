package com.solid.o;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Open/Closed Principle
 * 
 * We can use strategy design pattern 
 * 
 * from wikipedia
 * In object-oriented programming, the open/closed principle states "software entities (classes, modules, functions, etc.) should be open for extension, 
 * but closed for modification";[1] that is, such an entity can allow its behaviour to be extended without modifying its source code.
 * The name open/closed principle has been used in two ways. Both ways use generalizations (for instance, inheritance or delegate functions) to
 * resolve the apparent dilemma, but the goals, techniques, and results are different.
 * 
 * 
 * @author sk50921
 *
 * @param <T>
 */

public class SortManager<T> {

	public void doSorting(Collection<T> items, Sort<T> sorter) {
		sorter.sort(items);
	}

	public static void main(String[] args) {
		SortManager<Integer> sm = new SortManager<>();
		sm.doSorting(Stream.of(1, 2, 3).collect(Collectors.toList()), new MergeSort());
	}

}
