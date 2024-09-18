package com.tap.model;

import java.util.Date;

public class OrderTable {

	private int orderid;
	private int restaurantid;
	private int userid;
	private Date orderDate;
	private double totalamount;
	private String status;
	private String paymentmode;
	
	public OrderTable() {
		super();
	}
	
	public OrderTable(int orderid, int restaurantid, int userid,Date orderDate, double totalamount, String status, String paymentmode) {
		super();
		this.orderid = orderid;
		this.restaurantid = restaurantid;
		this.userid = userid;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentmode = paymentmode;
	}
	
	public OrderTable( int restaurantid, int userid,Date orderDate, float totalamount, String status, String paymentmode) {
		super();
		this.restaurantid = restaurantid;
		this.userid = userid;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentmode = paymentmode;
	}
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	@Override
	public String toString() {
		return "OrderTable [orderid=" + orderid + ", restaurantid=" + restaurantid + ", userid=" + userid
				+ ", orderDate=" + orderDate + ", totalamount=" + totalamount + ", status=" + status + ", paymentmode="
				+ paymentmode + "]";
	}
}
