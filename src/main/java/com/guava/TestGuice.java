package com.guava;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class TestGuice {
	
private void test(){
	Collections.unmodifiableList(Lists.newArrayList());
	Optional<Integer> possible = Optional.of(null);

	Splitter split = Splitter.on(",").omitEmptyStrings();
}
// return immutable list of name of 2 acive clientds
	private void testFluent() {
		Predicate<Client> activeClients = new Predicate<Client>() {
			public boolean apply(Client client) {
				return true;
			}
		};

		Iterable<Client> i = FluentIterable.from(getClientList()).filter(
				activeClients);
		
		Iterator<Client> iter = i.iterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		ImmutableList<Client> abc = order(Lists.newArrayList(i));
		
		iter = abc.iterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

public static List<Client> getClientList(){
	List<Client> clist = new ArrayList<>();
	TestGuice testG = new TestGuice();
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, 1);
    Date t = cal.getTime();
    System.out.println(t);
	Client c = testG.new Client("ankit",true,t );
	clist.add(c);
	cal.add(Calendar.DAY_OF_MONTH, -1);
	System.out.println(cal.getTime());
	c = testG.new Client("aniket", true, cal.getTime());
	clist.add(c);
	cal.add(Calendar.DAY_OF_MONTH, -1);
	System.out.println(cal.getTime());
	c = testG.new Client("pilu", false, cal.getTime());
	clist.add(c);
	return clist;
	
}

private ImmutableList<Client> order(List<Client> list){
	Ordering<Client> byLengthOrdering = new Ordering<Client>() {
		  public int compare(Client left, Client right) {
		    return left.getdateTime().compareTo(right.getdateTime());
		  }
		};
		
		ImmutableList<Client> sorted =byLengthOrdering.immutableSortedCopy(list);
		
		return sorted;
}
private class Client{
	String name;
	boolean isActiveLastMonth;
	Date dateTime;
	public Client(String name, boolean isActiveLastMonth, Date time) {
		super();
		this.name = name;
		this.isActiveLastMonth = isActiveLastMonth;
		this.dateTime=time;
	}
	@Override
	public String toString() {
		return "name-"+name +"acitve" +isActiveLastMonth;
	}
	public Date getdateTime(){
		return dateTime;
	}
}

private void getFromList(){
	List<String> list = Lists.newArrayList();
	list.add("1");
	list.add("1"); list.add("2");
	
}
public static void main(String[] args) {
	new TestGuice().testFluent();
}
}
