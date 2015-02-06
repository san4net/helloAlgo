package com.test.clsa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * {@link OrderSortUtility} for sorting {@link Order}
 * 
 * @author santosh kumar
 * @version 1.0
 * @Date 05/03/2012
 * @see Order
 */
public class OrderSortUtility {
	
    /**
     * Basic criterion for sorting the list of orders i.e orderid, quantity
     * @author ivy4467
     *
     */
	enum ORDER_SORTING_CRITERION{
		ORDER_ID,QUANTITY,PRICE,ORDER_DATE,ORDER_USER
	}
	
	/**
	 * Utility method to sort the orders based on varios criterion
	 * 
	 * @param orders
	 * @param criterion
	 */
 public static List<Order> sort(List<Order> orders, ORDER_SORTING_CRITERION criterion){
	 System.out.println("ordes before sorting:"+ orders);
	switch(criterion){
	case ORDER_ID:
		 Collections.sort(orders, getComparator(ORDER_SORTING_CRITERION.ORDER_ID));
		 break;
	case QUANTITY:
		 Collections.sort(orders, getComparator(ORDER_SORTING_CRITERION.QUANTITY));
		 break;
	case PRICE:
		 Collections.sort(orders, getComparator(ORDER_SORTING_CRITERION.PRICE));
		 break;
	case ORDER_DATE:
		 Collections.sort(orders, getComparator(ORDER_SORTING_CRITERION.ORDER_DATE));
		 break;
	case ORDER_USER:
		 Collections.sort(orders, getComparator(ORDER_SORTING_CRITERION.ORDER_USER));
		 break;
		 
	}
	
	System.out.println("ordes after sorting:"+ orders);
	return orders;
 }
 
 /**
  * Sorting comparator for different criterion
  * 
  * @param criterion
  * @return
  */
 public static Comparator getComparator(ORDER_SORTING_CRITERION criterion){
	 
	Comparator compartor = null;
			
	switch(criterion){	
	case ORDER_ID:	
		compartor =new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
			return (o1.getOrdereid()<o2.getOrdereid() ? -1 : (o1.getOrdereid()==o2.getOrdereid() ? 0 : 1));
		}
		 
	};
	break;
	
	case QUANTITY:	compartor =new Comparator<Order>() {

		@Override
		public int compare(Order o1, Order o2) {
			return (o1.getQuantity()<o2.getQuantity() ? -1 : (o1.getQuantity()==o2.getQuantity() ? 0 : 1));
		}
		 
	};
	break;
	
	case ORDER_DATE:	compartor =new Comparator<Order>() {

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getOrderDate().compareTo(o2.getOrderDate());
		}
		 
	};
	break;
	case PRICE:	compartor =new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
		  return Double.valueOf(o1.getPrice()).compareTo(o2.getPrice());
		}
		 
	};
	break;
	case ORDER_USER :	
		compartor = new Comparator<Order>() {
		@Override
		public int compare(Order o1, Order o2) {
		  return o1.getOrderUser().compareTo(o2.getOrderUser());
		}
		};
		break;
	}
	return compartor;
 }
 
 /**
  * This will create and give orders as per argument provided
  * 
  * @param noOfOrder
  * @return
  */
 public static List<Order> getOrder(int noOfOrder){
	 List<Order> orders = createOrder(noOfOrder);
	 duplicateOrderExceptOrderNo(orders, 1, 2);
 
	 return orders;
 }
 
 private static void duplicateOrderExceptOrderNo(List<Order> order,int origin, int destination){
	 Order order1 = order.get(origin);
	 Order order2 = order.get(destination);
	 
	 order2.setPrice(order1.getPrice());
	 order2.setQuantity(order1.getQuantity());
	 order2.setOrderDate(order1.getOrderDate());
	 order2.setOrderUser(order1.getOrderUser());
	 
 }
 
 /**
  * 
  * @param noOfOrder
  * @return
  */
 private static List<Order> createOrder(int noOfOrder){
	 List<Order> orderList = new ArrayList<Order>(noOfOrder);
	 int quantity =100;
	 double price =15;
	 Date orderdate = new Date();
	 Calendar calendar = Calendar.getInstance();
	 calendar.set(2013, 2, 1);
	 
	 Random r = new Random();
	 
	 String orderUser = "clsa_user";
	 for(int i =1;i<=noOfOrder;i++){
		 calendar.set(Calendar.DAY_OF_MONTH, i);
		 Order o = new Order(i, r.nextInt() ,  i+r.nextDouble(), calendar.getTime(), orderUser+i);
		 orderList.add(o);
		 
	 }
	 
	 return orderList;
 
 }
 
 public static void main(String[] args) {
	OrderSortUtility or = new OrderSortUtility();
	
	// 1. create 10 orders , here two order are duplicated except their orderid
	List<Order> orders = or.getOrder(10);
	
	//2. Sort the order based on various criterion
	
	sort(or.getOrder(10), ORDER_SORTING_CRITERION.ORDER_DATE);
	
	sort(or.getOrder(10), ORDER_SORTING_CRITERION.QUANTITY);
}
 
private long getRandom(Object o){
	if(o instanceof Integer){
		return new Random().nextInt();
	}else if(o instanceof Long){
		return  new Random().nextLong();
	}
	return 0;
	
}


}
