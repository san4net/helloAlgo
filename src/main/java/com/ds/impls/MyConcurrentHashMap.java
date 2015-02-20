package com.ds.impls;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Concurrent Hashmap custom implementation Only below custom functionalties are
 * provided
 * 
 * <pre>
 * 1. Concurrent insertions current concurrency level is set to 2
 * 
 * </pre>
 * 
 * @author santosh kumar
 *
 * @param <K>
 * @param <V>
 */
public class MyConcurrentHashMap<K, V> extends AbstractMap<K, V> {
	private AtomicInteger entryCount = new AtomicInteger(0);
	// This is array on entry
	private Object[] table;
	private final int size = 16;
	private final int CONCURRRENCY_LEVEL = 2;
	private ReentrantLock[] segmentLock;

	public MyConcurrentHashMap() {
		initialise();
	}

	/*
	 * Here put is synchronized per segment lock
	 */
	@Override
	public V put(K key, V value) {
		int bucketIndex = indexFor(key.hashCode(), size);
		return putUnderLock(bucketIndex, segmentIndex(bucketIndex), key, value);
	}

	private V putUnderLock(int bucketIndex, int segmentIndex, K key, V value) {
		ReentrantLock lock = segmentLock[segmentIndex];
		V oldValue = null;
		lock.lock();
		try {
			System.out.println("writing to segment :[" + segmentIndex
					+ "] time: " + System.currentTimeMillis());
			int hash = key.hashCode();
			if (table[bucketIndex] != null) {
				Entry<K, V> e = (Entry<K, V>) table[bucketIndex];
				Entry prev = null;
				for (; e != null; e = (Entry<K, V>) e.next) {
					Object k;
					if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
						oldValue = e.value;
						e.value = value;
					}
					prev = e;
				}
				// not able to find simple put this as next entry in the linked
				// list
				prev.next = newEntry(hash, key, value, null);
				entryCount.incrementAndGet();

				oldValue = null;
			} else {
				addEntry(hash, key, value, bucketIndex);
				oldValue = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return oldValue;
	}

	/**
	 * This get is not done under lock
	 * 
	 */
	@Override
	public V get(Object key) {
		return super.get(key);
	}

	private void initialise() {
		table = new Object[size];
		segmentLock = new ReentrantLock[CONCURRRENCY_LEVEL];
		// initializing segment lock;
		for (int i = 0; i < segmentLock.length; i++) {
			segmentLock[i] = new ReentrantLock(true);
		}
	}

	void addEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K, V> e = (Entry<K, V>) table[bucketIndex];
		table[bucketIndex] = newEntry(hash, key, value, e);
		entryCount.incrementAndGet();
	}

	Entry<K, V> newEntry(int hash, K key, V value, Object next) {
		return new Entry<>(hash, key, value, next);
	}

	int indexFor(int h, int length) {
		return h & (length - 1);
	}

	int segmentIndex(int bucketIndex) {
		return (segmentLock.length - 1) & (bucketIndex);
	}

	static class Entry<K, V> implements Map.Entry<K, V> {
		final K key;
		V value;
		Object next; // an Entry, or a TreeNode
		final int hash;

		/**
		 * Creates new entry.
		 */
		Entry(int h, K k, V v, Object n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			return false;
		}

		public final int hashCode() {
			return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
		}

		public final String toString() {
			return getKey() + "=" + getValue();
		}

		/**
		 * This method is invoked whenever the value in an entry is overwritten
		 * for a key that's already in the HashMap.
		 */
		void recordAccess(HashMap<K, V> m) {
		}

		/**
		 * This method is invoked whenever the entry is removed from the table.
		 */
		void recordRemoval(HashMap<K, V> m) {
		}
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new HashSet<>();

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				Entry<K, V> entry = (Entry<K, V>) table[i];
				while (entry != null) {
					set.add(entry);
					entry = (Entry<K, V>) entry.next;
				}

			}
		}
		return set;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				Entry<K, V> entry = (Entry<K, V>) table[i];
				while (entry != null) {
					set.add(entry.key);
					entry = (Entry<K, V>) entry.next;
				}

			}
		}
		return set;
	}

	public static void main(String[] args) {

		MyConcurrentHashMap<Integer, String> myMap = new MyConcurrentHashMap<>();
		// insert elemenent
		for (int i = 0; i < 10000; i++) {
			myMap.put(i, "software ag at key" + i);
		}
		System.out.println(myMap.keySet());
		System.out.println(myMap.entrySet());

		System.out.println(myMap.get(4));
		System.out.println(myMap.get(3));
		System.out.println(myMap.entryCount.intValue());
	}
}
