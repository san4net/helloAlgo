package com.test.clsa;

import java.util.Date;

/**
 * Below Class is Order class having attribute
 * 
 * @author santosh kumar
 * @version 1.0
 * @Date 05/03/2012
 *
 */
public class Order<T> implements Comparable<T> {
	private long ordereid;

	private long quantity;

	private double price;

	private Date orderDate;

	private String orderUser;

	public Order() {
		super();
	}

	public Order(long ordereid, long quantity, double price, Date orderdate,
			String orderuser) {
		super();
		this.ordereid = ordereid;
		this.quantity = quantity;
		this.price = price;
		this.orderDate = orderdate;
		this.orderUser = orderuser;
	}

	public long getOrdereid() {
		return ordereid;
	}

	public void setOrdereid(long ordereid) {
		this.ordereid = ordereid;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderdate) {
		this.orderDate = orderdate;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderuser) {
		this.orderUser = orderuser;
	}

	@Override
	public int compareTo(T o) {
		return 0;
	}

	public static void main(String[] args) {
		Order<String> o = new Order<String>();
		System.err.println(o.getOrdereid());
	}

	@Override
	public String toString() {
		return "Order [ordereid=" + ordereid + ", quantity=" + quantity
				+ ", price=" + price + ", orderDate=" + orderDate
				+ ", orderUser=" + orderUser + "]";
	}
	
}
